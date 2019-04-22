package org.softuni.finalpoject.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.models.binding.OtherActivityAddBindingModel;
import org.softuni.finalpoject.domain.models.service.OtherActivityServiceModel;
import org.softuni.finalpoject.domain.models.view.OtherActivityViewModel;
import org.softuni.finalpoject.service.OtherActivityService;
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
@RequestMapping("/otheractivities")
public class OtherActivityController extends BaseController {

    private final OtherActivityService otherActivityService;
    private final ModelMapper modelMapper;

    @Autowired
    public OtherActivityController(OtherActivityService otherActivityService, ModelMapper modelMapper) {
        this.otherActivityService = otherActivityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add Other Activity")
    public ModelAndView addOtherActivity(ModelAndView modelAndView, OtherActivityAddBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("otheractivity/add-otheractivity", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addOtherActivityConfirm(ModelAndView modelAndView,
                                        @Valid @ModelAttribute(name = "model") OtherActivityAddBindingModel model,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("otheractivity/add-otheractivity", modelAndView);
        }
        this.otherActivityService.addOtherActivity(this.modelMapper.map(model, OtherActivityServiceModel.class));
        return super.redirect("/otheractivities/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("All Other Activities")
    public ModelAndView allOtherActivities(ModelAndView modelAndView) {
        modelAndView.addObject("otheractivities", this.otherActivityService.findAllOtherActivities().stream()
                .map(o -> this.modelMapper.map(o, OtherActivityViewModel.class)).collect(Collectors.toList()));
        return super.view("otheractivity/all-otheractivities", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Other Activity")
    public ModelAndView editOtherActivity(@PathVariable String id,
                                     ModelAndView modelAndView,
                                     @ModelAttribute(name = "model") OtherActivityAddBindingModel model) throws NotFoundException {

        model = this.modelMapper.map(this.otherActivityService.findOtherActivityById(id), OtherActivityAddBindingModel.class);

        modelAndView.addObject("model", model);
        modelAndView.addObject("otheractivityId", id);

        return super.view("otheractivity/edit-otheractivity", modelAndView);
    }


    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editOtherActivityConfirm(@PathVariable String id,
                                            ModelAndView modelAndView,
                                            @Valid @ModelAttribute(name = "model") OtherActivityAddBindingModel model,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("otheractivity/edit-otheractivity", modelAndView);
        }

        this.otherActivityService.editOtherActivity(id, this.modelMapper.map(model, OtherActivityServiceModel.class));
        return super.redirect("/otheractivities/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Delete Other Activity")
    public ModelAndView deleteOtherActivity(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.otherActivityService.findOtherActivityById(id), OtherActivityViewModel.class));
        return super.view("otheractivity/delete-otheractivity", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteOtherActivityConfirm(@PathVariable String id) {
        this.otherActivityService.deleteOtherActivity(id);
        return super.redirect("/otheractivities/all");
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public List<OtherActivityViewModel> fetchOtheractivities() {
      return this.otherActivityService.findAllOtherActivities()
                .stream()
                .map(l -> this.modelMapper.map(l, OtherActivityViewModel.class))
                .collect(Collectors.toList());
    }
}
