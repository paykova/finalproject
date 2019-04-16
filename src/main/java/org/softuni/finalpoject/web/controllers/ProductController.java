package org.softuni.finalpoject.web.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.entities.Language;
import org.softuni.finalpoject.domain.entities.OtherActivity;
import org.softuni.finalpoject.domain.entities.Sport;
import org.softuni.finalpoject.domain.models.binding.ProductAddBindingModel;
import org.softuni.finalpoject.domain.models.service.*;
import org.softuni.finalpoject.service.*;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final LanguageService languageService;
    private final SportService sportService;
    private final InstrumentService instrumentService;
    private final OtherActivityService otherActivityService;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper, LanguageService languageService, SportService sportService, InstrumentService instrumentService, OtherActivityService otherActivityService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.languageService = languageService;
        this.sportService = sportService;
        this.instrumentService = instrumentService;
        this.otherActivityService = otherActivityService;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addProduct(ModelAndView modelAndView,
                                   @ModelAttribute(name = "bindingModel") ProductAddBindingModel bindingModel) {
      //  modelAndView.addObject("bindingModel", bindingModel);
        modelAndView.addObject("languages", this.languageService.findAllLanguages().stream()
                .map(l -> this.modelMapper.map(l, LanguageServiceModel.class))
                .collect(Collectors.toList()));
        modelAndView.addObject("sports", this.sportService.findAllSports().stream()
                .map(s -> this.modelMapper.map(s, SportServiceModel.class))
                .collect(Collectors.toList()));
        modelAndView.addObject("instruments", this.instrumentService.findAllInstruments().stream()
                .map(i -> this.modelMapper.map(i, InstrumentServiceModel.class))
                .collect(Collectors.toList()));
        modelAndView.addObject("otheractivities", this.otherActivityService.findAllOtherActivities().stream()
                .map(o -> this.modelMapper.map(o, OtherActivityServiceModel.class))
                .collect(Collectors.toList()));
        return super.view("product/add-product", modelAndView);
    }



//    @PostMapping("/add")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView addProductConfirm(@ModelAttribute ProductAddBindingModel model, ModelAndView modelAndView) throws IOException {
//        ProductServiceModel productServiceModel = this.modelMapper.map(model, ProductServiceModel.class);
//        productServiceModel.setLanguages(
//                this.languageService.findAllLanguages()
//                        .stream()
//                        .filter(c -> model.getLanguages().contains(c.getId()))
//                        .collect(Collectors.toList())
//        );
//
//        this.productService.createProduct(productServiceModel);
//
//        return super.redirect("/products/all");
 //   }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addConfirm(@ModelAttribute(name = "bindingModel") ProductAddBindingModel bindingModel,
                                   ModelAndView modelAndView) {
//        if (bindingResult.hasErrors()) {
//            modelAndView.addObject("bindingModel", bindingModel);
//
//            return super.view("product/add-product", modelAndView);
//        }

        ProductServiceModel productServiceModel = this.modelMapper.map(bindingModel, ProductServiceModel.class);
        this.populateLanguages(productServiceModel, bindingModel);
        this.populateInstruments(productServiceModel, bindingModel);
        this.populateSports(productServiceModel, bindingModel);
        this.populateOtherActivities(productServiceModel, bindingModel);

        this.productService.createProduct(productServiceModel);
        return super.redirect("/");
    }

    private void populateLanguages (ProductServiceModel productServiceModel, ProductAddBindingModel bindingModel) {
        List<Language> languageList = new ArrayList<>();
        for (Language language : bindingModel.getLanguages()) {
            Language entity;
            try {
                entity = this.languageService.findLanguageById(language.getId());
            } catch (NotFoundException e) {
                continue;
            }
            languageList.add(entity);
        }
        productServiceModel.setLanguages(languageList);
    }

    private void populateSports(ProductServiceModel productServiceModel, ProductAddBindingModel bindingModel) {
        List<Sport> sportList = new ArrayList<>();
        for (Sport sport : bindingModel.getSports()) {
            Sport entity;
            try {
                entity = this.sportService.findSportById(sport.getId());
            } catch (NotFoundException e) {
                continue;
            }
            sportList.add(entity);
        }
        productServiceModel.setSports(sportList);
    }

    private void populateInstruments(ProductServiceModel productServiceModel, ProductAddBindingModel bindingModel) {
        List<Instrument> instrumentList = new ArrayList<>();
        for (Instrument instrument : bindingModel.getInstruments()) {
            Instrument entity;
            try {
                entity = this.instrumentService.findInstrumentById(instrument.getId());
            } catch (NotFoundException e) {
                continue;
            }
            instrumentList.add(entity);
        }
        productServiceModel.setInstruments(instrumentList);
    }

    private void populateOtherActivities(ProductServiceModel productServiceModel, ProductAddBindingModel bindingModel) {
        List<OtherActivity> instrumentList = new ArrayList<>();
        for (OtherActivity otherActivity : bindingModel.getOtherActivities()) {
            OtherActivity entity;
            try {
                entity = this.otherActivityService.findOtherActivityById(otherActivity.getId());
            } catch (NotFoundException e) {
                continue;
            }
            instrumentList.add(entity);
        }
        productServiceModel.setOtherActivities(instrumentList);
    }
}
