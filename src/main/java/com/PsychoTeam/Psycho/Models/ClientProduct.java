package com.PsychoTeam.Psycho.Models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class ClientProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    private String color, url, name;
    private double price,size;
    private int quantity;

    public ClientProduct(){}

    public ClientProduct(Client client, Product product, double size, String color, double price, int quantity, String url, String name) {
        this.client = client;
        this.product = product;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.url = url;
        this.name = name;
    }

}
