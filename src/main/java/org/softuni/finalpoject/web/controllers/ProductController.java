package org.softuni.finalpoject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

//    private final ProductService productService;
//    private final ModelMapper modelMapper;
//    private final LanguageService languageService;
//    private final SportService sportService;
//    private final InstrumentService instrumentService;
//    private final OtherActivityService otherActivityService;
//    private final KidService kidService;
//
//    @Autowired
//    public ProductController(ProductService productService, ModelMapper modelMapper, LanguageService languageService, SportService sportService, InstrumentService instrumentService, OtherActivityService otherActivityService, KidService kidService) {
//        this.productService = productService;
//        this.modelMapper = modelMapper;
//        this.languageService = languageService;
//        this.sportService = sportService;
//        this.instrumentService = instrumentService;
//        this.otherActivityService = otherActivityService;
//        this.kidService = kidService;
//    }
//
//    @GetMapping("/add")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    @PageTitle("Add Product")
//    public ModelAndView addProduct() {
//        return super.view("product/add-product");
//    }
//
//
//    @PostMapping("/add")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView addProductConfirm(@ModelAttribute ProductAddBindingModel model) throws IOException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User customUser = (User)authentication.getPrincipal();
//
//        String userId = customUser.getId();
//        ProductServiceModel productServiceModel = this.modelMapper.map(model, ProductServiceModel.class);
//
//        productServiceModel.setKid(
//                this.kidService.findKidsByParent(customUser.getId())
//                        .stream()
//                        .filter(c -> model.getKids().contains(c.getId()))
//                        .collect(Collectors.toList())
//        );
//
//        productServiceModel.setLanguages(
//                this.languageService.findAllLanguages()
//                        .stream()
//                        .filter(c -> model.getLanguages().contains(c.getId()))
//                        .collect(Collectors.toList())
//        );
//
//
//        productServiceModel.setSports(
//                this.sportService.findAllSports()
//                        .stream()
//                        .filter(c -> model.getSports().contains(c.getId()))
//                        .collect(Collectors.toList())
//        );
//
////        InstrumentServiceModel instrumentServiceModel = this.modelMapper.map(model.getInstrument(), InstrumentServiceModel.class);
////
////        productServiceModel.setInstrument(instrumentServiceModel);
//
//
//
//        this.productService.createProduct(productServiceModel);
//        return super.redirect("/products/all");
//    }
//
//
//    @GetMapping("/all")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    @PageTitle("All Products")
//    public ModelAndView allProducts(ModelAndView modelAndView) {
//        modelAndView.addObject("products", this.productService.findAllProducts()
//                .stream()
//                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
//                .collect(Collectors.toList()));
//
//        return super.view("product/all-products", modelAndView);
//    }
//
//
//    @GetMapping("/details/{id}")
//    @PreAuthorize("isAuthenticated()")
//    @PageTitle("Product Details")
//    public ModelAndView detailsProduct(ModelAndView modelAndView, @PathVariable String id) {
//        ProductViewModel model = this.modelMapper.map(this.productService.findProductById(id), ProductViewModel.class);
//
//        modelAndView.addObject("product", model);
//
//        return super.view("product/details", modelAndView);
//    }


}
