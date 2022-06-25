package com.PsychoTeam.Psycho.controllers;

import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

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

    @GetMapping("/products/category")
    public ResponseEntity<Object> getProductsByCategory(
            @RequestParam String category,
            Authentication auth){

        if (category.isEmpty())
            return new ResponseEntity<>("Empty data", HttpStatus.FORBIDDEN);

        if (auth.getName() == null)
            return new ResponseEntity<>("Invalid data", HttpStatus.FORBIDDEN);

        List<ProductDTO> productDTOList = productService.getAllProductsByCategory(category).stream().map(ProductDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOList, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(
            @RequestParam String name, @RequestParam String description,
        @RequestParam int stock, @RequestParam double price, @RequestParam() ArrayList<Double> sizes,
            @RequestParam ArrayList<String> colors,@RequestParam String urlImg,
            @RequestParam String category, @RequestParam String filter,
            Authentication authentication) throws MessagingException, UnsupportedEncodingException {
            System.out.println(sizes);

            if(clientService.getClient(authentication.getName()) == null)
                return new ResponseEntity<>("You no have authorization", HttpStatus.FORBIDDEN);

            if (name.isEmpty() || description.isEmpty() || sizes.isEmpty() || colors.isEmpty())
                if (name.isEmpty() || description.isEmpty() || sizes.isEmpty() || colors.isEmpty() || urlImg.isEmpty() || category.isEmpty() || filter.isEmpty())
                    return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

            if(price < 0){
                return new ResponseEntity<>("Invalid price", HttpStatus.FORBIDDEN);
            }

            Product product = new Product(name, description, urlImg, stock, price, sizes, colors, category, filter);

            productService.saveProduct(product);

            return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
        }


        @Transactional
        @PostMapping("/products/modify")
        public ResponseEntity<Object> modifyProduct(
        @RequestParam int id,
        @RequestParam String name, @RequestParam String description,@RequestParam String urlImg,
        @RequestParam int stock, @RequestParam double price, @RequestParam() ArrayList<Double> sizes,
        @RequestParam ArrayList<String> colors, @RequestParam String category, @RequestParam String filter) throws MessagingException, UnsupportedEncodingException {

            if (name.isEmpty() || description.isEmpty() || sizes.isEmpty() || colors.isEmpty() || category.isEmpty() || filter.isEmpty())
                if (name.isEmpty() || description.isEmpty() || sizes.isEmpty() || colors.isEmpty() || urlImg.isEmpty())
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
            product.setCategory(category);
            product.setFilter(filter);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setUrlImg(urlImg);
            // ----------------------------- //
            productService.saveProduct(product);

            return new ResponseEntity<>("Product update successfully", HttpStatus.CREATED);
        }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Object> deleteProduct(
            @PathVariable long id,
            Authentication auth){

        if(auth.getName() == null || auth.getName() == ""){
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);
        }

        Product product = productService.getProductById(id);
        if(product == null){
            return new ResponseEntity<>("Product not found", HttpStatus.FORBIDDEN);
        }

        productService.removeProduct(product);
        return new ResponseEntity<>("Product has been removed", HttpStatus.ACCEPTED);

    }




}
