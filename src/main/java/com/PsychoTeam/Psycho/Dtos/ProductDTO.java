package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.Product;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ProductDTO {

    private long id;
    private String name,description,urlImg;
    private int stock;
    private double price;

    private List<Double> sizes = new ArrayList<>();

    private List<String> colors = new ArrayList<>();

    public String category;

    public String filter;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.sizes = product.getSizes();
        this.colors = product.getColors();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.urlImg = product.getUrlImg();
        this.category = product.getCategory();
        this.filter = product.getFilter();
    }


}
