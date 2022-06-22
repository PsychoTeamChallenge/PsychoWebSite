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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {
        return productService.getProductsDTO();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id) {

        if (id.isEmpty())
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        Product product = productService.getProductById(Integer.parseInt(id));

        if(product == null)
            return new ResponseEntity<>("Object not found", HttpStatus.FORBIDDEN);

        ProductDTO productDTO = new ProductDTO(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(
            @RequestParam String name, @RequestParam String description,
            @RequestParam int stock, @RequestParam double price, @RequestParam() ArrayList<Double> sizes, @RequestParam ArrayList<String> colors) throws MessagingException, UnsupportedEncodingException {
        System.out.println(sizes);
        if (name.isEmpty() || description.isEmpty() || sizes.isEmpty() || colors.isEmpty())
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        if(price < 0){
            return new ResponseEntity<>("Invalid price", HttpStatus.FORBIDDEN);
        }

        Product product = new Product(name, description, stock, price, sizes, colors);

        productService.saveProduct(product);

        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/products/modify")
    public ResponseEntity<Object> modifyProduct(
            @RequestParam int id,
            @RequestParam String name, @RequestParam String description,
            @RequestParam int stock, @RequestParam double price, @RequestParam() ArrayList<Double> sizes, @RequestParam ArrayList<String> colors) throws MessagingException, UnsupportedEncodingException {

        if (name.isEmpty() || description.isEmpty() || sizes.isEmpty() || colors.isEmpty())
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        if(price < 0)
            return new ResponseEntity<>("Invalid price", HttpStatus.FORBIDDEN);

        if(productService.getProductById(id) == null){
            return new ResponseEntity<>("Invalid data", HttpStatus.FORBIDDEN);
        }

        Product product = productService.getProductById(id);
        // ----------------------------- //
        product.setClientProducts(product.getClientProducts());
        product.setColors(colors);
        product.setSizes(sizes);
        product.setDescription(description);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        // ----------------------------- //
        productService.saveProduct(product);

        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }



}
