package com.PsychoTeam.Psycho.services.implement;
import com.PsychoTeam.Psycho.Dtos.PostDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.Models.Post;
import com.PsychoTeam.Psycho.repositories.PostRepository;
import com.PsychoTeam.Psycho.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplement implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<PostDTO> getPostDTO() {
        return postRepository.findAll().stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<Post> getByClient(Client client) {
        return postRepository.findByClient(client);
    }

    @Override
    public void saveCard(Post post) {
        postRepository.save(post);
    }
}
