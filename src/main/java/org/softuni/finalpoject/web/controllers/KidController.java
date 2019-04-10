package org.softuni.finalpoject.web.controllers;


import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.models.binding.KidAddBindingModel;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.service.KidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView addKid(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") KidAddBindingModel bindingModel) {
        modelAndView.addObject("bindingModel", bindingModel);
        return super.view("kid/kid-add", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addKidConfirm(@Valid @ModelAttribute(name = "bindingModel") KidAddBindingModel bindingModel,
                                      BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingModel", bindingModel);

            return super.view("kid/kid-add", modelAndView);
        }
        KidServiceModel kidServiceModel = this.modelMapper.map(bindingModel, KidServiceModel.class);
        this.kidService.addKid(kidServiceModel);

        return super.redirect("/home");
    }
}
