package com.PsychoTeam.Psycho.services;

import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    List<Product> getProducts();
    List<ProductDTO> getProductsDTO();
    boolean existProduct(long id);
    void saveClientProduct(ClientProduct clientProduct);
    void saveProduct(Product product);

}
