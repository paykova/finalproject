package org.softuni.finalpoject.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.models.binding.SportAddBindingModel;
import org.softuni.finalpoject.domain.models.service.SportServiceModel;
import org.softuni.finalpoject.domain.models.view.SportViewModel;
import org.softuni.finalpoject.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/sports")
public class SportController extends BaseController{

    private final SportService sportService;
    private final ModelMapper modelMapper;

    @Autowired
    public SportController(SportService sportService, ModelMapper modelMapper) {
        this.sportService = sportService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addSport(){
        return super.view("sport/add-sport");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
   public ModelAndView addSportConfirm(@ModelAttribute SportAddBindingModel model){
        this.sportService.addSport(this.modelMapper.map(model, SportServiceModel.class));

        return super.redirect("/sports/all");
   }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
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
    public ModelAndView editSport(@PathVariable String id, ModelAndView modelAndView){
        modelAndView.addObject("model", this.modelMapper.map(this.sportService.findSportById(id), SportViewModel.class));
        return super.view("sport/edit-sport", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editSportConfirm(@PathVariable String id, @ModelAttribute SportAddBindingModel model){
        this.sportService.editSport(id, this.modelMapper.map(model, SportServiceModel.class));
        return super.redirect("/sports/all");
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteSport(@PathVariable String id, ModelAndView modelAndView){
        modelAndView.addObject("model", this.modelMapper.map(this.sportService.findSportById(id), SportViewModel.class));
        return super.view("sport/delete-sport", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteSportConfirm(@PathVariable String id){

        this.sportService.deleteSport(id);
        return super.redirect("/sports/all");
    }


}
