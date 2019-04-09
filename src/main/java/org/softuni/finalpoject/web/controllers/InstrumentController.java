package org.softuni.finalpoject.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.models.binding.InstrumentAddBindingModel;
import org.softuni.finalpoject.domain.models.service.InstrumentServiceModel;
import org.softuni.finalpoject.domain.models.view.InstrumentViewModel;
import org.softuni.finalpoject.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/instruments")
public class InstrumentController extends BaseController  {

    private final InstrumentService instrumentService;
    private final ModelMapper modelMapper;

    @Autowired
    public InstrumentController(InstrumentService instrumentService, ModelMapper modelMapper) {
        this.instrumentService = instrumentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addInstrument(){
        return super.view("instrument/add-instrument");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addInstrumentConfirm(@ModelAttribute InstrumentAddBindingModel model){
        this.instrumentService.addInstrument(this.modelMapper.map(model, InstrumentServiceModel.class));
        return super.redirect("/instruments/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allInstrument(ModelAndView modelAndView){
        modelAndView.addObject("instruments", this.instrumentService.findAllInstruments().stream()
        .map(i -> this.modelMapper.map(i, InstrumentViewModel.class)).collect(Collectors.toList()));
        return super.view("instrument/all-instruments", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editInstrument(@PathVariable String id, ModelAndView modelAndView){
        modelAndView.addObject("model",
                this.modelMapper.map(this.instrumentService.findInstrumentById(id), InstrumentViewModel.class));
        return super.view("instrument/edit-instrument", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editInstrumentConfirm(@PathVariable String id, @ModelAttribute InstrumentAddBindingModel model){
        this.instrumentService.editInstrument(id, this.modelMapper.map(model, InstrumentServiceModel.class));
        return super.redirect("/instruments/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteInstrument(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.instrumentService.findInstrumentById(id), InstrumentViewModel.class));
        return super.view("instrument/delete-instrument", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteInstrumentConfirm(@PathVariable String id) {

        this.instrumentService.deleteInstrument(id);
        return super.redirect("/instruments/all");
    }

}
