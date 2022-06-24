package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.Purchase;
import com.PsychoTeam.Psycho.Models.ShipmentType;
import com.lowagie.text.Document;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class PurchaseDTO {

    private long id;
    private long idCrypt;
    private long clientId;
    private Set<ClientProduct> products;
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
        this.products = purchase.getProducts();
        this.date = purchase.getDate();
        this.totalExpense = purchase.getTotalExpense();
        this.shipmentType = purchase.getShipmentType();
        this.payMethod = purchase.getPayMethod();
        this.address = purchase.getAddress();
        this.enable = purchase.isEnable();
    }

}
