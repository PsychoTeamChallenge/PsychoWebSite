package com.PsychoTeam.Psycho.Dtos;

import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.ClientProduct;
import com.PsychoTeam.Psycho.Models.Post;
import com.PsychoTeam.Psycho.Models.Product;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ClientDTO {

    private long id;
    private String firstName,lastName,userName, email;

    private Set<ClientProductDTO> cart;

    private Set<ProductDTO> favourites;
    private Set<PostDTO> posts;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.userName = client.getUserName();
        this.email = client.getEmail();
        this.cart = client.getCart().stream().map(clientProduct -> new ClientProductDTO(clientProduct)).collect(Collectors.toSet());
        this.favourites = client.getFavourites().stream().map(product -> new ProductDTO(product)).collect(Collectors.toSet());
        this.posts = client.getPosts().stream().map(post -> new PostDTO(post)).collect(Collectors.toSet());
    }


}
