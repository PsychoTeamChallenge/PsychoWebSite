package com.PsychoTeam.Psycho.Models;

import com.lowagie.text.Document;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.PsychoTeam.Psycho.Utils.Utils.GenerateIdCrypt;

@Setter
@Getter
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private long idCrypt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ElementCollection
    @JoinColumn(name = "product_cart")
    private Set<ProductCart> productCarts = new HashSet<>();
    private LocalDateTime date;
    private double totalExpense;
    private ShipmentType shipmentType;
    private String payMethod;
    private String address;

    public boolean enable;

    public Purchase() {}

    public Purchase(Client client, double totalExpense, ShipmentType shipmentType, String payMethod, String address) {
        this.client = client;
        this.date = LocalDateTime.now();
        this.totalExpense = totalExpense;
        this.shipmentType = shipmentType;
        this.payMethod = payMethod;
        this.address = address;
        this.idCrypt = GenerateIdCrypt();
    }
    public void addProduct(ProductCart productCart){
       this.productCarts.add(productCart);
    }

}
