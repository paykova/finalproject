package org.softuni.finalpoject.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.constants.Constants;
import org.softuni.finalpoject.domain.entities.Sport;
import org.softuni.finalpoject.domain.models.binding.SportAddBindingModel;
import org.softuni.finalpoject.domain.models.service.SportServiceModel;
import org.softuni.finalpoject.domain.models.view.SportViewModel;
import org.softuni.finalpoject.service.SportService;
import org.softuni.finalpoject.validation.SportEditValidator;
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
@RequestMapping("/sports")
public class SportController extends BaseController {

    private final SportService sportService;
    private final ModelMapper modelMapper;
    private final SportEditValidator editValidator;

    @Autowired
    public SportController(SportService sportService, ModelMapper modelMapper, SportEditValidator editValidator) {
        this.sportService = sportService;
        this.modelMapper = modelMapper;
        this.editValidator = editValidator;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_ADD_SPORT)
    public ModelAndView addSport(ModelAndView modelAndView, SportAddBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("sport/add-sport", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addSportConfirm(ModelAndView modelAndView,
                                        @Valid @ModelAttribute(name = "model") SportAddBindingModel model,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("sport/add-sport", modelAndView);
        }
        this.sportService.addSport(this.modelMapper.map(model, SportServiceModel.class));
        return super.redirect("/sports/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_ALL_SPORTS)
    public ModelAndView allSports(ModelAndView modelAndView) {
        modelAndView.addObject("sports",
                this.sportService.findAllSports()
                        .stream()
                        .map(s -> this.modelMapper.map(s, SportViewModel.class))
                        .collect(Collectors.toList())
        );

        return super.view("sport/all-sports", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_EDIT_SPORT)
    public ModelAndView editSport(@PathVariable String id,
                                  ModelAndView modelAndView,
                                  @ModelAttribute(name = "model") SportAddBindingModel model) {


        model = this.modelMapper.map(this.sportService.findSportById(id), SportAddBindingModel.class);


        modelAndView.addObject("sportId", id);
        modelAndView.addObject("model", model);

        return super.view("sport/edit-sport", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editSportConfirm(@PathVariable String id,
                                         ModelAndView modelAndView,
                                         @ModelAttribute(name = "model") SportAddBindingModel model,
                                         BindingResult bindingResult) {
         this.editValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("sport/edit-sport", modelAndView);
        }

        this.sportService.editSport(id, this.modelMapper.map(model, SportServiceModel.class));
        return super.redirect("/sports/all");
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_DELETE_SPORT)
    public ModelAndView deleteSport(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("model", this.modelMapper.map(this.sportService.findSportById(id), SportViewModel.class));
        return super.view("sport/delete-sport", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteSportConfirm(@PathVariable String id) {

        this.sportService.deleteSport(id);
        return super.redirect("/sports/all");
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public List<SportViewModel> fetchSports() {
        return this.sportService.findAllSports()
                .stream()
                .map(l -> this.modelMapper.map(l, SportViewModel.class))
                .collect(Collectors.toList());
    }
}
