package com.PsychoTeam.Psycho.Models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private long productId;
    private String color, url, name,description;
    private double price,size;
    private int quantity;

    public ProductCart(){}

    public ProductCart(ClientProduct clientProduct) {
        this.id = clientProduct.getId();
        this.size = clientProduct.getSize();
        this.color = clientProduct.getColor();
        this.price = clientProduct.getPrice();
        this.quantity = clientProduct.getQuantity();
        this.url = clientProduct.getUrl();
        this.name = clientProduct.getName();
        this.description = clientProduct.getProduct().getDescription();
    }

}
