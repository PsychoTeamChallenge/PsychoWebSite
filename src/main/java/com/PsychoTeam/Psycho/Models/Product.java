package com.PsychoTeam.Psycho.Models;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name,description,urlImg, category, filter;
    private int stock;
    private double price;

    @ElementCollection
    @Column(name="sizes")
    private List<Double> sizes = new ArrayList<>();

    @ElementCollection
    @Column(name="colors")
    private List<String> colors = new ArrayList<>();

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    private Set<ClientProduct> clientProducts = new HashSet<>();

    public Product(){}

    public Product(String name, String description,String urlImg, int stock, double price, String category, String filter) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.sizes = sizes;
        this.colors = colors;
        this.urlImg = urlImg;
        this.category = category;
        this.filter = filter;
    }

    public void addClientProduct(ClientProduct clientProduct) {
        clientProduct.setProduct(this);
        clientProducts.add(clientProduct);
    }

    public void addColor(String color){
        this.colors.add(color);
    }
    public void addSize(double size){
        this.sizes.add(size);
    }
}
