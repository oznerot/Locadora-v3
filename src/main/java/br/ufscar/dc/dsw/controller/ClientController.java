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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.service.spec.IClientService;

@Controller
@RequestMapping("/clients")
public class ClientController
{
    @Autowired
    private IClientService service;

    @GetMapping("/register")
    public String register(Client client)
    {
        return "client/register";
    }

    @GetMapping("/list")
    public String list(ModelMap model)
    {
        model.addAttribute("clients", service.findAll());
        return "client/list";
    }

    @PostMapping("/save")
    public String save(@Valid Client client, BindingResult result, RedirectAttributes attr)
    {
        if(result.hasErrors())
        {
            return "client/register";
        }

        service.save(client);
        attr.addFlashAttribute("success", "Client registered successfully.");
        return "redirect:/clients/list";
    }

    @GetMapping("/edit/{id}")
    public String preEdit(@PathVariable("id") Long id, ModelMap model)
    {
        model.addAttribute("clients", service.findById(id));
        return "client/register";
    }

    @PostMapping("/edit")
    public String edit(@Valid Client client, BindingResult result, RedirectAttributes attr)
    {
        if(result.getFieldErrorCount() > 1 || result.getFieldError("email") == null)
        {
            return "client/register";
        }

        service.save(client);
        attr.addFlashAttribute("success", "Client updated successfully.");
        return "redirect:/clients/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelMap model)
    {
        service.delete(id);
        model.addAttribute("success", "Client deleted successfully.");
        return list(model);
    }
}