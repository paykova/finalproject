package org.softuni.finalpoject.service;

import org.softuni.finalpoject.domain.models.service.ProductServiceModel;

public interface ProductService {

    ProductServiceModel createProduct(ProductServiceModel productServiceModel);

    ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel);

    ProductServiceModel findProductById(String id);

    void deleteProduct(String id);

}
