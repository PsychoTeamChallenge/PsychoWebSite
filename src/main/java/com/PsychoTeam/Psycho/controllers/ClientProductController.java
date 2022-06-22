package com.PsychoTeam.Psycho.controllers;

import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.services.ClientProductService;
import com.PsychoTeam.Psycho.services.ClientService;
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
public class ClientProductController {

    @Autowired
    private ClientProductService clientProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/cart")
    public List<ProductDTO> getProducts(){
        return productService.getProductsDTO();
    }

    /*@GetMapping("/products/{id}")
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
    }*/

    @PostMapping("/cart")
    public ResponseEntity<Object> createProduct(
            @RequestParam String size, @RequestParam String color,
            @RequestParam double price, @RequestParam int quantity, @RequestParam int id_client, @RequestParam int id_product) throws MessagingException, UnsupportedEncodingException {

        Product product = productService.getProductById(id_product);
        Client client = clientService.getClientById(id_client);

        if (size.isEmpty() || color.isEmpty())
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        if (product == null || client == null)
            return new ResponseEntity<>("Invalid data", HttpStatus.FORBIDDEN);

        ClientProduct clientProduct = new ClientProduct(client, product, size, color, price, quantity);

        client.addProductCart(clientProduct); // Add ClientProduct to Client
        product.addClientProduct(clientProduct); // Add ClientProduct to Product

        clientService.saveClient(client); // Save client
        productService.saveProduct(product); // Save product
        clientProductService.saveClientProduct(clientProduct); // Save ClientProduct

        return new ResponseEntity<>("Product added successfully",HttpStatus.CREATED);
    }

}
