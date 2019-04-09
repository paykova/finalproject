package org.softuni.finalpoject.web.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index(){
        return super.view("index");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(){
        return super.view("home");
    }


    @GetMapping("/contact")
    @PreAuthorize("isAnonymous()")
    public ModelAndView contact(){
        return super.view("contact");
    }

    @GetMapping("/contact-logged")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView contactLogged(){
        return super.view("contact");
    }
}
