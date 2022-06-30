package com.PsychoTeam.Psycho.Models;

import com.PsychoTeam.Psycho.Dtos.ClientProductDTO;
import com.PsychoTeam.Psycho.Dtos.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName,lastName,userName, email, password, token;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<ClientProduct> cart = new HashSet<>();

    @ElementCollection
    @Column(name="favourites")
    private Set<Product> favourites = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Purchase> purchases = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

    private boolean enabled;

    public Client(){}

    public Client(String firstName,String lastName, String userName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public String getFullName(){
        return this.firstName + " " + this.getLastName();
    }

    public void addProductCart(ClientProduct product) {
        product.setClient(this);
        cart.add(product);
    }

    public void addFavourite(Product product) {
        favourites.add(product);
    }

    public void removeFavourite(Product product) {
        favourites.remove(product);
    }
    public void addPost(Post post) {
        post.setClient(this);
        posts.add(post);
    }

    public void addPurchases(Purchase purchase){
        purchase.setClient(this);
        purchases.add(purchase);
    }

    public void addAppointments(Appointment appointment){
        appointment.setClient(this);
        appointments.add(appointment);
    }

    public void deleteToken() {
        this.token = "";
    }
    public boolean isEnabled() {
        return this.enabled;
    }
    public void emptyCart(){
        this.cart.forEach(clientProduct -> cart.remove(clientProduct));
    }
}
