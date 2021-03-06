package org.softuni.finalpoject.web.controllers;


import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.constants.Constants;
import org.softuni.finalpoject.domain.models.binding.KidAddBindingModel;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.domain.models.view.KidAllViewModel;
import org.softuni.finalpoject.domain.models.view.KidViewModel;
import org.softuni.finalpoject.service.*;
import org.softuni.finalpoject.utils.ValidationUtil;
import org.softuni.finalpoject.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/kids")
public class KidController extends BaseController {

    private final KidService kidService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final LanguageService languageService;
    private final InstrumentService instrumentService;
    private final OtherActivityService otherActivityService;
    private final SportService sportService;
    private final ValidationUtil validationUtil;

    @Autowired
    public KidController(KidService kidService,
                         ModelMapper modelMapper,
                         CloudinaryService cloudinaryService,
                         LanguageService languageService,
                         InstrumentService instrumentService,
                         OtherActivityService otherActivityService,
                         SportService sportService, ValidationUtil validationUtil) {
        this.kidService = kidService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.languageService = languageService;
        this.instrumentService = instrumentService;
        this.otherActivityService = otherActivityService;
        this.sportService = sportService;
        this.validationUtil = validationUtil;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(Constants.PAGE_TITLE_ADD_KID)
    public ModelAndView addKid(ModelAndView modelAndView,
                               @ModelAttribute(name = "model") KidAddBindingModel model) {

        modelAndView.addObject("model", model);
        return super.view("kid/add-kid", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addKidConfirm(@Valid @ModelAttribute(name = "model") KidAddBindingModel model,
                                      BindingResult bindingResult,
                                      ModelAndView modelAndView,
                                      Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {

            modelAndView.addObject("model", model);
            return super.view("kid/add-kid", modelAndView);
        }

        if ((model.getBirthDate()).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(Constants.KID_BIRTH_DATE_MUST_NOT_BE_IN_THE_FUTURE);
        }

        KidServiceModel kidServiceModel = this.modelMapper.map(model, KidServiceModel.class);

        kidServiceModel.setImageUrl(
                this.cloudinaryService.uploadImage(model.getImage()));

        kidServiceModel.setLanguages(
                this.languageService.findAllLanguages()
                        .stream()
                        .filter(l -> model.getLanguages().contains(l.getId()))
                        .collect(Collectors.toList())
        );

        kidServiceModel.setInstruments(
                this.instrumentService.findAllInstruments()
                        .stream()
                        .filter(i -> model.getInstruments().contains(i.getId()))
                        .collect(Collectors.toList())
        );

        kidServiceModel.setSports(
                this.sportService.findAllSports()
                        .stream()
                        .filter(s -> model.getSports().contains(s.getId()))
                        .collect(Collectors.toList())
        );

        kidServiceModel.setOtheractivities(
                this.otherActivityService.findAllOtherActivities()
                        .stream()
                        .filter(o -> model.getOtheractivities().contains(o.getId()))
                        .collect(Collectors.toList())
        );

        this.kidService.addKid(kidServiceModel, principal.getName());

        return super.redirect("/home");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle(Constants.PAGE_TITLE_ALL_KIDS)
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
    @PageTitle(Constants.PAGE_TITLE_MY_KIDS)
    public ModelAndView getMyKids(ModelAndView modelAndView, @PathVariable String id) {

        List<KidViewModel> kidViewModels = kidService.findKidsByParent(id)
                .stream()
                .map(k -> modelMapper.map(k, KidViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("kids", kidViewModels);
        return view("kid/my-kids", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(Constants.PAGE_TITLE_KID_DETAILS)
    public ModelAndView detailsKid(@PathVariable String id, ModelAndView modelAndView) {

        KidViewModel model = this.modelMapper.map(this.kidService.findKidById(id), KidViewModel.class);

        modelAndView.addObject("kid", model);

        return super.view("kid/kid-details", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_EDIT_KID)
    public ModelAndView editKid(@PathVariable String id,
                                ModelAndView modelAndView, @ModelAttribute(name = "model") KidAddBindingModel bindingModel) throws IOException {

        KidServiceModel kidServiceModel = this.kidService.findKidById(id);
        KidAddBindingModel model = this.modelMapper.map(kidServiceModel, KidAddBindingModel.class);

        model.setLanguages(kidServiceModel.getLanguages().stream().map(l -> l.getName()).collect(Collectors.toList()));
        model.setInstruments(kidServiceModel.getInstruments().stream().map(i -> i.getName()).collect(Collectors.toList()));
        model.setSports(kidServiceModel.getSports().stream().map(s -> s.getName()).collect(Collectors.toList()));
        model.setOtheractivities(kidServiceModel.getOtheractivities().stream().map(o -> o.getName()).collect(Collectors.toList()));



        modelAndView.addObject("kid", model);
        modelAndView.addObject("kidId", id);

        return super.view("kid/edit-kid", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editKidConfirm(@PathVariable String id,
                                       ModelAndView modelAndView,
                                       BindingResult bindingResult,
                                       @ModelAttribute KidAddBindingModel model,
                                       Principal principal) throws IOException {

        if (!this.validationUtil.isValid(model)) {
            throw new IllegalArgumentException(Constants.TRYING_TO_ADD_INVALID_DATA);
        }
        if (bindingResult.hasErrors()) {

            modelAndView.addObject("model", model);
            return super.view("kid/add-kid", modelAndView);
        }

        if ((model.getBirthDate()).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(Constants.KID_BIRTH_DATE_MUST_NOT_BE_IN_THE_FUTURE);
        }


        KidServiceModel kidServiceModel = this.modelMapper.map(model, KidServiceModel.class);
        kidServiceModel.setId(id);
        kidServiceModel.setParent(this.kidService.findKidById(id).getParent());

        kidServiceModel.setLanguages(
                this.languageService.findAllLanguages()
                        .stream()
                        .filter(l -> model.getLanguages().contains(l.getId()))
                        .collect(Collectors.toList())
        );
        kidServiceModel.setInstruments(
                this.instrumentService.findAllInstruments()
                        .stream()
                        .filter(i -> model.getInstruments().contains(i.getId()))
                        .collect(Collectors.toList())
        );

        kidServiceModel.setSports(
                this.sportService.findAllSports()
                        .stream()
                        .filter(s -> model.getSports().contains(s.getId()))
                        .collect(Collectors.toList())
        );

        kidServiceModel.setOtheractivities(
                this.otherActivityService.findAllOtherActivities()
                        .stream()
                        .filter(o -> model.getOtheractivities().contains(o.getId()))
                        .collect(Collectors.toList())
        );

        this.kidService.editKid(id, kidServiceModel,  principal.getName());
        // return super.redirect("/kid/kid-details/" + id);
        return super.redirect("/home");
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_DELETE_KID)
    public ModelAndView deleteKid(@PathVariable String id, ModelAndView modelAndView) {

        KidServiceModel kidServiceModel = this.kidService.findKidById(id);
        KidAddBindingModel model = this.modelMapper.map(kidServiceModel, KidAddBindingModel.class);
        model.setLanguages(kidServiceModel.getLanguages().stream().map(l -> l.getName()).collect(Collectors.toList()));
        model.setInstruments(kidServiceModel.getInstruments().stream().map(i -> i.getName()).collect(Collectors.toList()));
        model.setSports(kidServiceModel.getSports().stream().map(s -> s.getName()).collect(Collectors.toList()));
        model.setOtheractivities(kidServiceModel.getOtheractivities().stream().map(o -> o.getName()).collect(Collectors.toList()));

        modelAndView.addObject("kid", model);
        modelAndView.addObject("kidId", id);

        return super.view("kid/delete-kid", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteProductConfirm(@PathVariable String id) {
        this.kidService.deleteKid(id);

        return super.redirect("/home");
    }

    @GetMapping("/schedule/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(Constants.PAGE_TITLE_KID_SCHEDULE)
    public ModelAndView scheduleKid(@PathVariable String id, ModelAndView modelAndView) {

        KidServiceModel kidServiceModel = this.kidService.findKidById(id);
        KidAddBindingModel model = this.modelMapper.map(kidServiceModel, KidAddBindingModel.class);
        model.setLanguages(kidServiceModel.getLanguages().stream().map(l -> l.getName()).collect(Collectors.toList()));
        model.setInstruments(kidServiceModel.getInstruments().stream().map(i -> i.getName()).collect(Collectors.toList()));
        model.setSports(kidServiceModel.getSports().stream().map(s -> s.getName()).collect(Collectors.toList()));
        model.setOtheractivities(kidServiceModel.getOtheractivities().stream().map(o -> o.getName()).collect(Collectors.toList()));

        modelAndView.addObject("kid", model);
        modelAndView.addObject("kidId", id);

        return super.view("kid/kid-schedule", modelAndView);
    }
}



