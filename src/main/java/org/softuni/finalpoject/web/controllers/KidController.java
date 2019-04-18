package org.softuni.finalpoject.web.controllers;


import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.models.binding.KidAddBindingModel;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.domain.models.view.KidAllViewModel;
import org.softuni.finalpoject.domain.models.view.KidViewModel;
import org.softuni.finalpoject.service.*;
import org.softuni.finalpoject.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/kids")
public class KidController extends BaseController {

    private final KidService kidService;
    private final UserService userService;
    private final LanguageService languageService;
    private final InstrumentService instrumentService;
    private final SportService sportService;
    private final OtherActivityService otherActivityService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public KidController(KidService kidService, UserService userService, LanguageService languageService, InstrumentService instrumentService, SportService sportService, OtherActivityService otherActivityService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.kidService = kidService;
        this.userService = userService;
        this.languageService = languageService;
        this.instrumentService = instrumentService;
        this.sportService = sportService;
        this.otherActivityService = otherActivityService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add Kid")
    public ModelAndView addKid(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") KidAddBindingModel bindingModel) {
        modelAndView.addObject("languageNames", this.languageService.getLanguagesNames());
        modelAndView.addObject("instrumentNames", this.instrumentService.getInstrumentNames());
        modelAndView.addObject("sportNames", this.sportService.getSportsNames());
        modelAndView.addObject("otheractivityNames", this.otherActivityService.getOtherActivitiesNames());
        return super.view("kid/add-kid", modelAndView);
    }


//    public ModelAndView addKidConfirm(@Valid @ModelAttribute(name = "bindingModel") KidAddBindingModel bindingModel,
//                                      BindingResult bindingResult, ModelAndView modelAndView, Principal principal) {
//        if (bindingResult.hasErrors()) {
//            modelAndView.addObject("bindingModel", bindingModel);
//
//            return super.view("kid/add-kid", modelAndView);
//        }
//        String name = principal.getName();
//        KidServiceModel kidServiceModel = this.modelMapper.map(bindingModel, KidServiceModel.class);
//        this.kidService.addKid(kidServiceModel, name);
//
//        return super.redirect("/home");
//    }
@PostMapping("/add")
@PreAuthorize("isAuthenticated()")
    public ModelAndView addKidConfirm(ModelAndView modelAndView, @Valid @ModelAttribute(name = "bindingModel") KidAddBindingModel model,
                                          BindingResult bindingResult) throws IOException, NotFoundException {

        if (bindingResult.hasErrors()) {
            return super.view("kid/add-kid", modelAndView);
        }
        KidServiceModel kidServiceModel = this.modelMapper.map(model, KidServiceModel.class);

        kidServiceModel.setLanguage(this.languageService.findLanguageById(model.getLanguageId()));
        kidServiceModel.setInstrument(this.instrumentService.findInstrumentById(model.getInstrumentId()));
        kidServiceModel.setSport(this.sportService.findSportById(model.getSportId()));
        kidServiceModel.setOtheractivity(this.otherActivityService.findOtherActivityById(model.getOtheractivityId()));
        kidServiceModel.setImageUrl(this.cloudinaryService.uploadImage(model.getImage()));

        this.kidService.addKidd(kidServiceModel);

        return super.redirect("/home");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("All Kids")
    public ModelAndView allKids(ModelAndView modelAndView) {
        List<KidAllViewModel> viewModels = kidService.findAllKids()
                .stream()
                .map(k -> modelMapper.map(k, KidAllViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("kids", viewModels);
        return view("kid/all-kids", modelAndView);
    }


    @GetMapping("/my/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @PageTitle("My Kids")
    public ModelAndView getMyKids(ModelAndView modelAndView, @PathVariable String id) {
        List<KidViewModel> kidViewModels = kidService.findKidsByParent(id)
                .stream()
                .map(k -> modelMapper.map(k, KidViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("kids", kidViewModels);
        return view("kid/my-kids", modelAndView);
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @ResponseBody
    public List<KidViewModel> fetchKids() {
        List<KidViewModel> kids = this.kidService.findAllKids()
                .stream()
                .map(l -> this.modelMapper.map(l, KidViewModel.class))
                .collect(Collectors.toList());
        return kids;
    }
}



