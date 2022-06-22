package com.PsychoTeam.Psycho.controllers;

import com.PsychoTeam.Psycho.Dtos.ClientProductDTO;
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
import org.springframework.security.config.web.servlet.headers.HttpStrictTransportSecurityDsl;
import org.springframework.security.core.Authentication;
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

    @PatchMapping("/cart/modify")
    public ResponseEntity<Object> modifyProduct(
            @RequestParam int clientproduct_id, @RequestParam int quantity){
        if(clientProductService.getClientProductById(clientproduct_id) == null){
            return new ResponseEntity<>("Invalid data", HttpStatus.FORBIDDEN);
        }
        ClientProduct clientProduct = clientProductService.getClientProductById(clientproduct_id);
        clientProduct.setQuantity(quantity);
        clientProductService.saveClientProduct(clientProduct);
        return new ResponseEntity<>("Product modified correctly", HttpStatus.ACCEPTED);
    }

    @GetMapping("/cart/current")
    public ResponseEntity<Object> getProductsOfClient(Authentication auth){
        if(auth.getName() == null){
            return new ResponseEntity<>("Invalid authentication credentials", HttpStatus.FORBIDDEN);
        }
        Client client = clientService.getClient(auth.getName());
        List<ClientProductDTO> cartListDTO = clientProductService.getClientProductsByClient(client);
        return new ResponseEntity<>(cartListDTO, HttpStatus.ACCEPTED);
    }

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

    @DeleteMapping("/cart")
    public ResponseEntity<Object> removeProductFromCart(
            @RequestParam int clientproduct_id){

        if(clientProductService.getClientProductById(clientproduct_id) == null){
            return new ResponseEntity<>("Invalid data", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Product removed successfully", HttpStatus.ACCEPTED);
    }
}
