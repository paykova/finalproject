package org.softuni.finalpoject.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.*;
import org.softuni.finalpoject.domain.models.binding.ProductAddBindingModel;
import org.softuni.finalpoject.domain.models.service.ProductServiceModel;
import org.softuni.finalpoject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final LanguageService languageService;
    private final SportService sportService;
    private final ModelMapper modelMapper;
    private final InstrumentService instrumentService;
    private final OtherActivityService otherActivityService;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            LanguageService languageService,
            SportService sportService,
            ModelMapper modelMapper,
            InstrumentService instrumentService, OtherActivityService otherActivityService) {
        this.productRepository = productRepository;
        this.languageService = languageService;
        this.sportService = sportService;
        this.modelMapper = modelMapper;
        this.instrumentService = instrumentService;
        this.otherActivityService = otherActivityService;
    }


    @Override
    public ProductServiceModel createProduct(ProductServiceModel productServiceModel) {
//        Product product = this.productRepository
//                .findById(productServiceModel.getId())
//                .orElse(null);
//
//        if (product != null) {
//            throw new IllegalArgumentException("Product already exists");
//        }

        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product = this.productRepository.save(product);

        return this.modelMapper.map(product, ProductServiceModel.class);
    }


    @Override
    public ProductServiceModel findProductById(String id) {
        Product product = this.productRepository.findById(id).orElse(null);
        if(product == null){
            throw new IllegalArgumentException("Invalid id");
        }
        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel) {
//        Product product = this.productRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Product with the given id was not found!"));
//
//        productServiceModel.setLanguages(
//                this.languageService.findAllLanguages()
//                        .stream()
//                        .filter(p -> productServiceModel.getLanguages().contains(p.getId()))
//                        .collect(Collectors.toList())
//        );
//
//        productServiceModel.setSports(
//                this.sportService.findAllSports()
//                        .stream()
//                        .filter(c -> productServiceModel.getSports().contains(c.getId()))
//                        .collect(Collectors.toList())
//        );
//
//        product.setName(productServiceModel.getName());
//        product.setDescription(productServiceModel.getDescription());
//        product.setPrice(productServiceModel.getPrice());
//        product.setLanguages(
//                productServiceModel.getLanguages()
//                        .stream()
//                        .map(l -> this.modelMapper.map(l, Language.class))
//                        .collect(Collectors.toList()));
//        product.setSports(
//                productServiceModel.getSports()
//                        .stream()
//                        .map(s -> this.modelMapper.map(s, Sport.class))
//                        .collect(Collectors.toList()));
//        product.setInstruments(
//                productServiceModel.getInstruments()
//                        .stream()
//                        .map(i -> this.modelMapper.map(i, Instrument.class))
//                        .collect(Collectors.toList()));
//        product.setOtherActivities(
//                productServiceModel.getOtherActivities()
//                        .stream()
//                        .map(o -> this.modelMapper.map(o, OtherActivity.class))
//                        .collect(Collectors.toList()));

       // return this.modelMapper.map(this.productRepository.saveAndFlush(product), ProductServiceModel.class);
        return null;
    }


    @Override
    public void deleteProduct(String id) {

    }
//
//    private void populateLanguages(ProductServiceModel productServiceModel, ProductAddBindingModel bindingModel) {
//        List<Language> languageList = new ArrayList<>();
//        for (Language language : bindingModel.getLanguages()) {
//            Language entity;
//            try {
//                entity = this.languageService.findLanguageById(language.getId());
//            } catch (NotFoundException e) {
//                continue;
//            }
//            languageList.add(entity);
//        }
//        productServiceModel.setLanguages(languageList);
//    }
}
