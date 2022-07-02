package com.PsychoTeam.Psycho.Models;

import com.PsychoTeam.Psycho.Dtos.ClientProductDTO;
import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Appointment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    private long phone;

    private String bodyPart;

    private String tattooSize;

    private boolean color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tattoer_id")
    private Tattoer tattoer;

    private LocalDate date;

    public Appointment(){
    }
    public Appointment(Client client, long phone, String bodyPart, String tattooSize, boolean color, Tattoer tattoer, LocalDate date){
        this.client = client;
        this.phone = phone;
        this.bodyPart = bodyPart;
        this.tattooSize = tattooSize;
        this.color = color;
        this.tattoer = tattoer;
        this.date = date;
    }
}
