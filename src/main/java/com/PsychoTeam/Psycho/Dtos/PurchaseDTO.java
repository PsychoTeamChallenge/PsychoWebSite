package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.ProductCart;
import com.PsychoTeam.Psycho.Models.Purchase;
import com.PsychoTeam.Psycho.Models.ShipmentType;
import com.lowagie.text.Document;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class PurchaseDTO {

    private long id;
    private long idCrypt;
    private long clientId;
    private Set<ProductCartDTO> productsCart;
    private LocalDateTime date;
    private double totalExpense;
    private ShipmentType shipmentType;
    private String payMethod;
    private String address;
    public boolean enable;


    public PurchaseDTO(){}

    public PurchaseDTO(Purchase purchase){
        this.id = purchase.getId();
        this.idCrypt = purchase.getIdCrypt();
        this.clientId = purchase.getClient().getId();
        this.productsCart = purchase.getProductCarts().stream().map(productCart -> new ProductCartDTO(productCart)).collect(Collectors.toSet());
        this.date = purchase.getDate();
        this.totalExpense = purchase.getTotalExpense();
        this.shipmentType = purchase.getShipmentType();
        this.payMethod = purchase.getPayMethod();
        this.address = purchase.getAddress();
        this.enable = purchase.isEnable();
    }

}
