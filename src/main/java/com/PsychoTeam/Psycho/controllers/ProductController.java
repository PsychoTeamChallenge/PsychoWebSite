package com.PsychoTeam.Psycho.controllers;

import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        return productService.getProductsDTO();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@RequestParam String id){
        if(id.isEmpty()){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        } else {
            ProductDTO productDTO = new ProductDTO(productService.getProductById(Integer.parseInt(id)));
            if(productDTO == null){
                return new ResponseEntity<>("Object not found", HttpStatus.FORBIDDEN);
            }

            return new ResponseEntity<>(productDTO, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(
            @RequestParam String name, @RequestParam String description,
            @RequestParam int stock, @RequestParam double price, @RequestParam List<Double> sizes, @RequestParam List<String> colors) throws MessagingException, UnsupportedEncodingException {

        if (name.isEmpty() || description.isEmpty() || sizes.isEmpty() || colors.isEmpty())
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        Product product = new Product(name, description, stock, price, sizes, colors);

        productService.saveProduct(product);

        return new ResponseEntity<>("Product created successfully",HttpStatus.CREATED);
    }
}
