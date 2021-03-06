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
    public Post getPostById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<PostDTO> getPostsDTO() {
        return postRepository.findAll().stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<Post> getByClient(Client client) {
        return postRepository.findByClient(client);
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void removePost(Post post) {
        postRepository.delete(post);
    }
}
