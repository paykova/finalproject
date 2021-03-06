package org.softuni.finalpoject.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.constants.Constants;
import org.softuni.finalpoject.domain.models.binding.LanguageAddBindingModel;
import org.softuni.finalpoject.domain.models.binding.SportAddBindingModel;
import org.softuni.finalpoject.domain.models.service.LanguageServiceModel;
import org.softuni.finalpoject.domain.models.view.LanguageViewModel;
import org.softuni.finalpoject.service.LanguageService;
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
@RequestMapping("languages")
public class LanguageController extends BaseController {

    private final LanguageService languageService;
    private final ModelMapper modelMapper;


    @Autowired
    public LanguageController(LanguageService languageService, ModelMapper modelMapper) {
        this.languageService = languageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_ADD_LANGUAGE)
    public ModelAndView addLanguage(ModelAndView modelAndView, LanguageAddBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("language/add-language", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addLanguageConfirm(ModelAndView modelAndView,
                                        @Valid @ModelAttribute(name = "model") LanguageAddBindingModel model,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("language/add-language", modelAndView);
        }
        this.languageService.addLanguage(this.modelMapper.map(model, LanguageServiceModel.class));
        return super.redirect("/languages/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_ALL_LANGUAGES)
    public ModelAndView allLanguages(ModelAndView modelAndView) {
        modelAndView.addObject("languages", this.languageService.findAllLanguages()
                        .stream()
                        .map(l -> this.modelMapper.map(l, LanguageViewModel.class))
                        .collect(Collectors.toList())
        );
        return super.view("language/all-languages", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_EDIT_LANGUAGE)
    public ModelAndView editLanguage(@PathVariable String id,
                                  ModelAndView modelAndView,
                                  @ModelAttribute(name = "model") LanguageAddBindingModel model) throws NotFoundException {


        model = this.modelMapper.map(this.languageService.findLanguageById(id), LanguageAddBindingModel.class);


        modelAndView.addObject("languageId", id);
        modelAndView.addObject("model", model);

        return super.view("language/edit-language", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editLanguageConfirm(@PathVariable String id,
                                         ModelAndView modelAndView,
                                         @Valid @ModelAttribute(name = "model") LanguageAddBindingModel model,
                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("language/edit-language", modelAndView);
        }

        this.languageService.editLanguage(id, this.modelMapper.map(model, LanguageServiceModel.class));
        return super.redirect("/languages/all");
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(Constants.PAGE_TITLE_DELETE_LANGUAGE)
    public ModelAndView deleteLanguage(@PathVariable String id, ModelAndView modelAndView) throws NotFoundException {
        modelAndView.addObject("model",
                this.modelMapper.map(this.languageService.findLanguageById(id), LanguageViewModel.class));
        return super.view("language/delete-language", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteLanguageConfirm(@PathVariable String id) {

        this.languageService.deleteLanguage(id);
        return super.redirect("/languages/all");
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public List<LanguageViewModel> fetchLanguages() {
      return  this.languageService.findAllLanguages()
                .stream()
                .map(l -> this.modelMapper.map(l, LanguageViewModel.class))
                .collect(Collectors.toList());
    }
}
