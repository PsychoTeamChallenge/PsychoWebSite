package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.ClientProduct;
import lombok.Getter;

@Getter
public class ClientProductDTO {

    private long id,idProduct;
    private String color, url, name;
    private double  size,price;
    private int quantity;

    public ClientProductDTO(ClientProduct clientProduct){
        this.id = clientProduct.getId();
        this.idProduct = clientProduct.getProduct().getId();
        this.size = clientProduct.getSize();
        this.color = clientProduct.getColor();
        this.price = clientProduct.getPrice();
        this.quantity = clientProduct.getQuantity();
        this.url = clientProduct.getUrl();
        this.name = clientProduct.getName();
    }
}
