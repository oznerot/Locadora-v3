package br.ufscar.dc.dsw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userPage(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("Username: " + userDetails.getUsername());
            model.addAttribute("remoteUser", userDetails.getUsername()); // Add the user to the model
        }
        return "user/index"; // Return the view name
    }
}