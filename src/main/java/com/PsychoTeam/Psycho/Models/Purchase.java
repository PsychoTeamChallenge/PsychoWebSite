package com.PsychoTeam.Psycho.Models;

import com.lowagie.text.Document;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(name = "products")
    private Set<ClientProduct> products;
    private LocalDateTime date;
    private long totalExpense;
    private ShipmentType shipmentType;
    private String payMethod;
    private String address;
    private Document pdf;

    public Purchase() {}

    public Purchase(Client client, long totalExpense, ShipmentType shipmentType, String payMethod, String address, Document pdf) {
        this.client = client;
        this.products = client.getCart();
        this.date = LocalDateTime.now();
        this.totalExpense = totalExpense;
        this.shipmentType = shipmentType;
        this.payMethod = payMethod;
        this.address = address;
        this.pdf = pdf;
        this.idCrypt = GenerateIdCrypt();
    }

}
