package com.PsychoTeam.Psycho.services.implement;

import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.repositories.ClientProductRepository;
import com.PsychoTeam.Psycho.repositories.ProductRepository;
import com.PsychoTeam.Psycho.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ClientProductRepository clientProductRepository;

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDTO> getProductsDTO() {
        return  productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean existProduct(long id) {
        return productRepository.existsById(id);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
