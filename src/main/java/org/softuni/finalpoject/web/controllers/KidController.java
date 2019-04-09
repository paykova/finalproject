package org.softuni.finalpoject.web.controllers;


import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.service.KidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/kids")
public class KidController extends BaseController {

    private final KidService kidService;
    private final ModelMapper modelMapper;

    @Autowired
    public KidController(KidService kidService, ModelMapper modelMapper) {
        this.kidService = kidService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addKid() {
        return super.view("contact-logged");
    }

//    @PostMapping("/add")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView addKidConfirm(@ModelAttribute(name= "bindingModel") KidAddBindingModel model) {
//        this.kidService.addKid(this.modelMapper.map(model, KidServiceModel.class));
//        return super.redirect("/home");
//    }
}
