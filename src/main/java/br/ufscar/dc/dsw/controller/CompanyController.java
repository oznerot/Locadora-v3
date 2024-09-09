package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Company;
import br.ufscar.dc.dsw.service.spec.ICompanyService;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private ICompanyService service;

    @GetMapping("/register")
    public String register(Company company)
    {
        return "company/register";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "city", required = false) String city, ModelMap model)
    {
        if(city == null || city.isEmpty() || city.equalsIgnoreCase("all"))
        {
            model.addAttribute("companies", service.findAll());
        }
        else
        {
            model.addAttribute("companies", service.findByCity(city));
        }

        List<String> availableCities = service.findAvailableCities();
        model.addAttribute("companies", service.findAll());
        
        return "company/list";
    }

    @PostMapping("/save")
    public String save(@Valid Company company, BindingResult result, RedirectAttributes attr)
    {
        if(result.hasErrors())
        {
            return "company/register";
        }

        service.save(company);
        attr.addFlashAttribute("success", "Company registered successfully.");
        return "redirect:/company/list";
    }

    @GetMapping("/edit/{id}")
    public String preEdit(@PathVariable("id") Long id, ModelMap model)
    {
        model.addAttribute("company", service.findById(id));
        return "company/register";
    }

    @PostMapping("/edit")
    public String edit(@Valid Company company, BindingResult result, RedirectAttributes attr)
    {
        if(result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null)
        {
            return "company/register";
        }

        service.save(company);
        attr.addFlashAttribute("success", "Company updated successfully.");
        return "redirect:/companies/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelMap model)
    {
        service.delete(id);
        model.addAttribute("success", "Company deleted successfully.");
        return list(null, model);   
    }
}