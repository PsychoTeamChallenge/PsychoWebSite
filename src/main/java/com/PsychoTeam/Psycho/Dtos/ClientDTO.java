package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.Post;
import com.PsychoTeam.Psycho.Models.Product;
import lombok.Getter;

import java.util.Set;

@Getter
public class ClientDTO {

    private long id;
    private String firstName,lastName,userName, email, password;

    private Set<ClientProduct> cart;

    private Set<Product> favourites;
    private Set<Post> post;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.userName = client.getUserName();
        this.email = client.getEmail();
        this.cart = client.getCart();
        this.favourites = client.getFavourites();
        this.post = client.getPosts();
    }


}
