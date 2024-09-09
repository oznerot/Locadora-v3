package br.ufscar.dc.dsw.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Company;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import br.ufscar.dc.dsw.service.spec.ICompanyService;
import br.ufscar.dc.dsw.service.spec.IClientService;
import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.security.MyUserDetails;

@Controller
@RequestMapping("/rentals")
public class RentalController
{
    @Autowired
    private IRentalService service;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IClientService clientService;

    private User getUser()
    {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    @GetMapping("/register")
    public String register(Rental rental, Model model)
    {
        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        return "rental/register";
    }

    @PostMapping("/save")
    public String save(@Valid Rental rental, BindingResult result, RedirectAttributes attr, Authentication auth, Model model)
    {
        if(result.hasErrors())
        {
            List<Company> companies = companyService.findAll();
            model.addAttribute("companies", companies);
            return "rental/register";
        }

        String email = auth.getName();
        Client loggedClient = clientService.findByEmail(email);
        rental.setClient(loggedClient);

        service.save(rental);
        attr.addFlashAttribute("success", "Rental registeres successfully");
        return "redirect:/rentals/list";
    }

    @GetMapping("/edit/{id}")
    public String preEdit(@PathVariable("id") Long id, ModelMap model, Authentication auth)
    {
        model.addAttribute("rental", service.findById(id));

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        return "rental/register";
    }

    @PostMapping("/edit")
    public String edit(@Valid Rental rental, BindingResult result, RedirectAttributes attr)
    {
        service.save(rental);
        attr.addFlashAttribute("success", "Rental updated successfully.");
        return "redirect:/rentals/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelMap model)
    {
        service.delete(id);
        model.addAttribute("success", "Rental deleted successfully.");
        return list(model);
    }

    @GetMapping("/list")
    public String list(ModelMap model)
    {
        User user = this.getUser();
        String role = user.getRole();
    
        if(role.equals("ROLE_CLIENT"))
            model.addAttribute("rentals", service.findAllByClient(user.getId()));
        else
            model.addAttribute("rentals", service.findAllByCompany(user.getId()));
        
        return "rental/list";
    }
}