package com.example.twitter_demo.registration;


import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;


    @PostMapping("api/registration")
    public String registerUser(@RequestBody Registration registration){
        return registrationService.registerUser(registration);
    }
    //session id is deleted on /logout
    @GetMapping("api/home")
    public String home(Model model, HttpSession session){
        model.addAttribute("sessionId", session.getId());
        return "home. Authenticated and session id is " + session.getId();
    }
    @GetMapping("api/test")
    public String testing(Model model, HttpSession session){
        model.addAttribute("sessionId", session.getId());
        return "test. Authenticated and session id is " + session.getId();
    }

}
