package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.ProductCart;
import lombok.Getter;

@Getter
public class ProductCartDTO {

    private long id,idProduct;
    private String color, url, name, description;
    private double  size,price;
    private int quantity;

    public ProductCartDTO(ProductCart productCart){
        this.id = productCart.getId();
        this.idProduct = productCart.getProductId();
        this.size = productCart.getSize();
        this.color = productCart.getColor();
        this.price = productCart.getPrice();
        this.quantity = productCart.getQuantity();
        this.url = productCart.getUrl();
        this.name = productCart.getName();
        this.description = productCart.getDescription();
    }
}
