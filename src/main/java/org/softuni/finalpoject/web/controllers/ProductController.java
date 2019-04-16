package org.softuni.finalpoject.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.models.binding.ProductAddBindingModel;
import org.softuni.finalpoject.domain.models.service.*;
import org.softuni.finalpoject.service.*;
import org.softuni.finalpoject.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
    private final KidService kidService;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper, LanguageService languageService, SportService sportService, InstrumentService instrumentService, OtherActivityService otherActivityService, KidService kidService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.languageService = languageService;
        this.sportService = sportService;
        this.instrumentService = instrumentService;
        this.otherActivityService = otherActivityService;
        this.kidService = kidService;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Kids Center - Add Product")
    public ModelAndView addProduct() {
        return super.view("product/add-producttt");
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addProductConfirm(@ModelAttribute ProductAddBindingModel model) throws IOException {
        ProductServiceModel productServiceModel = this.modelMapper.map(model, ProductServiceModel.class);

//        productServiceModel.setKid(
//                this.kidService.findKidById()
//                        .stream()
//                        .filter(c -> model.getKid().contains(c.getId()))
//                        .collect(Collectors.toList())
//        );

        productServiceModel.setLanguages(
                this.languageService.findAllLanguages()
                        .stream()
                        .filter(c -> model.getLanguages().contains(c.getId()))
                        .collect(Collectors.toList())
        );
        productServiceModel.setSports(
                this.sportService.findAllSports()
                        .stream()
                        .filter(c -> model.getSports().contains(c.getId()))
                        .collect(Collectors.toList())
        );

        this.productService.createProduct(productServiceModel);

        return super.view("product/details-product");
    }

//    @GetMapping("/details/{id}")
//    @PreAuthorize("isAuthenticated()")
//    @PageTitle("Kids Center - Product Details")
//    public ModelAndView detailsProduct(@PathVariable String id, ModelAndView modelAndView) {
//        ProductDetailsViewModel model = this.modelMapper.map(this.productService.findProductById(id), ProductDetailsViewModel.class);
//
//        modelAndView.addObject("product", model);
//
//        return super.view("product/details", modelAndView);
//    }
}
