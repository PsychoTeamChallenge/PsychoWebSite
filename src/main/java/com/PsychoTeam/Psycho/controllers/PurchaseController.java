package com.PsychoTeam.Psycho.controllers;

import com.PsychoTeam.Psycho.Dtos.PurchaseApplicationDTO;
import com.PsychoTeam.Psycho.Dtos.PurchaseDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;

import com.PsychoTeam.Psycho.Models.Purchase;
import com.PsychoTeam.Psycho.services.ClientProductService;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.ProductService;
import com.PsychoTeam.Psycho.services.PurchaseService;
import com.lowagie.text.Document;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class PurchaseController {

    @Autowired
    private ClientProductService clientProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchases/current")
    public ResponseEntity<Object> getPurchasesClient(Authentication auth){
        if (auth.getName() == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        Client clientUsed = clientService.getClient(auth.getName());

        if (clientUsed == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        List<PurchaseDTO> purchaseDTOList = purchaseService.getPurchasesDTOByClient(clientUsed);

        return new ResponseEntity<>(purchaseDTOList, HttpStatus.ACCEPTED);
    }

    @Transactional
    @PostMapping("/purchase/complete")
    public ResponseEntity<Object> completePurchase(@RequestBody PurchaseApplicationDTO purchaseApplicationDTO, Authentication auth){

        if (auth.getName() == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        Client clientUsed = clientService.getClient(auth.getName());

        if (clientUsed == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        List<ClientProduct> clientProductList = clientProductService.getClientProductsByClientNotDTO(clientUsed);

        if (clientProductList.size() == 0)
            return new ResponseEntity<>("Not a single item in the cart", HttpStatus.FORBIDDEN);


        LocalDate todayDate = LocalDate.now();
        LocalDateTime todayTime = todayDate.atTime(LocalTime.now());

        double totalExpense = clientProductService.getTotalExpensesOfCart(clientUsed);

        Set<ClientProduct> productsList = new HashSet<>(clientProductList);

        Purchase newPurchase = new Purchase();
        newPurchase.setProducts(productsList);
        newPurchase.setDate(todayTime);
        newPurchase.setAddress(purchaseApplicationDTO.getAddress());
        newPurchase.setPayMethod(purchaseApplicationDTO.getPaymentMethod());
        newPurchase.setPdf(purchaseApplicationDTO.getPdf());
        newPurchase.setEnable(true);
        clientUsed.addPurchases(newPurchase);
        clientService.saveClient(clientUsed);
        purchaseService.savePurchase(newPurchase);

        return new ResponseEntity<>("Purchase completed", HttpStatus.ACCEPTED);
    }

    @Transactional
    @DeleteMapping("/purchase/delete")
    public ResponseEntity<Object> removeProductFromCart(
            @RequestParam int purchase_id,
            Authentication auth){

        Purchase purchase = purchaseService.getPurchaseById(purchase_id);

        if(purchase == null){
            return new ResponseEntity<>("Invalid data", HttpStatus.FORBIDDEN);
        }

        if(auth.getName() == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        purchaseService.removePurchase(purchase);
        return new ResponseEntity<>("Product removed successfully", HttpStatus.ACCEPTED);
    }
}
