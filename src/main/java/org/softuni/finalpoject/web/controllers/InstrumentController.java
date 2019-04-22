package org.softuni.finalpoject.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.constants.Constants;
import org.softuni.finalpoject.domain.models.binding.InstrumentAddBindingModel;
import org.softuni.finalpoject.domain.models.service.InstrumentServiceModel;
import org.softuni.finalpoject.domain.models.view.InstrumentViewModel;
import org.softuni.finalpoject.service.InstrumentService;
import org.softuni.finalpoject.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/instruments")
public class InstrumentController extends BaseController {

    private final InstrumentService instrumentService;
    private final ModelMapper modelMapper;

    @Autowired
    public InstrumentController(InstrumentService instrumentService, ModelMapper modelMapper) {
        this.instrumentService = instrumentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_ADD_INSTRUMENT)
    public ModelAndView addInstrument(ModelAndView modelAndView, InstrumentAddBindingModel model) {

        modelAndView.addObject("model", model);
        return super.view("instrument/add-instrument", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addInstrumentConfirm(ModelAndView modelAndView,
                                             @Valid @ModelAttribute(name = "model") InstrumentAddBindingModel model,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("instrument/add-instrument", modelAndView);
        }

        this.instrumentService.addInstrument(this.modelMapper.map(model, InstrumentServiceModel.class));

        return super.redirect("/instruments/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_ALL_INSTRUMENTS)
    public ModelAndView allInstrument(ModelAndView modelAndView) {
        modelAndView.addObject("instruments", this.instrumentService.findAllInstruments().stream()
                .map(i -> this.modelMapper.map(i, InstrumentViewModel.class)).collect(Collectors.toList()));
        return super.view("instrument/all-instruments", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_EDIT_INSTRUMENT)
    public ModelAndView editInstrument(@PathVariable String id,
                                       ModelAndView modelAndView,
                                       @ModelAttribute(name = "model") InstrumentAddBindingModel model) {


        model = this.modelMapper.map(this.instrumentService.findInstrumentById(id), InstrumentAddBindingModel.class);

        modelAndView.addObject("instrumentId", id);
        modelAndView.addObject("model", model);

        return super.view("instrument/edit-instrument", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editInstrumentConfirm(@PathVariable String id,
                                              ModelAndView modelAndView,
                                              @Valid @ModelAttribute(name = "model") InstrumentAddBindingModel model,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("instrument/edit-instrument", modelAndView);
        }

        this.instrumentService.editInstrument(id, this.modelMapper.map(model, InstrumentServiceModel.class));
        return super.redirect("/instruments/all");
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_DELETE_INSTRUMENT)
    public ModelAndView deleteInstrument(@PathVariable String id,
                                         ModelAndView modelAndView) throws NotFoundException {

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

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public List<InstrumentViewModel> fetchInstruments() {
        List<InstrumentViewModel> instruments = this.instrumentService.findAllInstruments()
                .stream()
                .map(i -> this.modelMapper.map(i, InstrumentViewModel.class))
                .collect(Collectors.toList());
        return instruments;
    }

}
