package com.PsychoTeam.Psycho.controllers;

import com.PsychoTeam.Psycho.Dtos.PurchaseApplicationDTO;
import com.PsychoTeam.Psycho.Dtos.PurchaseDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;

import com.PsychoTeam.Psycho.Models.ProductCart;
import com.PsychoTeam.Psycho.Models.Purchase;
import com.PsychoTeam.Psycho.repositories.ProductCartRepository;
import com.PsychoTeam.Psycho.services.ClientProductService;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.ProductService;
import com.PsychoTeam.Psycho.services.PurchaseService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.PsychoTeam.Psycho.Utils.Utils.CreatePDF;
import static com.PsychoTeam.Psycho.Utils.Utils.GenerateToken;


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
    ProductCartRepository productCartRepository;

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
    public ResponseEntity<Object> completePurchase(HttpServletResponse response, @RequestBody PurchaseApplicationDTO purchaseApplicationDTO, Authentication auth) throws DocumentException, IOException {

        if (auth.getName() == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        Client clientUsed = clientService.getClient(auth.getName());

        if (clientUsed == null)
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);

        List<ClientProduct> clientProductList = clientProductService.getClientProductsByClientNotDTO(clientUsed);

        if (clientProductList.size() == 0)
            return new ResponseEntity<>("Not a single item in the cart", HttpStatus.FORBIDDEN);


        double totalExpense = clientProductService.getTotalExpensesOfCart(clientUsed);

        Purchase newPurchase = new Purchase(clientUsed,totalExpense,purchaseApplicationDTO.getShipmentType(), purchaseApplicationDTO.getPaymentMethod(), purchaseApplicationDTO.getAddress());
        newPurchase.setEnable(true);

        clientUsed.getCart().forEach(clientProduct -> {
            ProductCart productCart = new ProductCart(clientProduct);
            productCartRepository.save(productCart);
            newPurchase.addProduct(productCart);
        });

        purchaseService.savePurchase(newPurchase);


        clientUsed.addPurchases(newPurchase);
        clientService.saveClient(clientUsed);
        clientProductService.removeClientProducts(clientUsed);
        return new ResponseEntity<>("Purchase completed", HttpStatus.ACCEPTED);
    }


    @GetMapping("/purchase/resume")
    public ResponseEntity<?> getResume(Authentication authentication, HttpServletResponse response, @RequestParam long idPurchase) throws DocumentException, IOException {

        Client client = clientService.getClient(authentication.getName());

        if(client == null)
            return new ResponseEntity<>("Invalid Client", HttpStatus.FORBIDDEN);

        Purchase purchase = client.getPurchases().stream().filter(purchaseClient -> purchaseClient.getId() == idPurchase).findAny().orElse(null);

        if(purchase == null)
        return new ResponseEntity<>("Invalid Purchase", HttpStatus.FORBIDDEN);

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm") ;
        String currentDateTime = dateFormatter.format(new Date());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Psycho_Resume_" + currentDateTime + ".pdf";
        response.setHeader(headerKey,headerValue);


        CreatePDF(response,purchase);
        return new ResponseEntity<>("PDF SEND", HttpStatus.CREATED);

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
