package com.PsychoTeam.Psycho.controllers;


import com.PsychoTeam.Psycho.Dtos.PostDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Post;
import com.PsychoTeam.Psycho.Models.PostType;
import com.PsychoTeam.Psycho.Models.Product;
import com.PsychoTeam.Psycho.services.ClientService;
import com.PsychoTeam.Psycho.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private ClientService clientService;

    @Autowired
    PostService postService;

    @GetMapping("/post")
    public ResponseEntity<?> getAllPost() {
        List<PostDTO> posts = postService.getPostsDTO();
        return new ResponseEntity(posts, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/post")
    public ResponseEntity<?> createPost(Authentication authentication, @RequestParam String urlImage,
                                        @RequestParam String tattooer, @RequestParam PostType postType, @RequestParam int fires) {

        if (urlImage.isEmpty() || tattooer.isEmpty())
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        if (fires < 0 || fires > 5)
            return new ResponseEntity<>("Fires invalid", HttpStatus.FORBIDDEN);

        Client client = clientService.getClient(authentication.getName());

        if (client == null)
            return new ResponseEntity<>("User no have Client rol", HttpStatus.FORBIDDEN);

        Post post = new Post( "title", urlImage, "description", tattooer, postType, fires);
        client.addPost(post);

        postService.savePost(post);
        clientService.saveClient(client);
        return new ResponseEntity<>("Post created", HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/post/delete/{id}")
    public ResponseEntity<Object> deletePost(
            @PathVariable long id,
            Authentication auth){

        if(auth.getName() == null || auth.getName() == ""){
            return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);
        }

        Post post = postService.getPostById(id);
        if(post == null){
            return new ResponseEntity<>("Post not found", HttpStatus.FORBIDDEN);
        }

        postService.removePost(post);
        return new ResponseEntity<>("Post has been removed", HttpStatus.ACCEPTED);

    }
}