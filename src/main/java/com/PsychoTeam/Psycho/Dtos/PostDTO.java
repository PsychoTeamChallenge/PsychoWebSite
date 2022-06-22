package com.PsychoTeam.Psycho.Dtos;
import com.PsychoTeam.Psycho.Models.Post;
import com.PsychoTeam.Psycho.Models.PostType;
import lombok.Getter;


@Getter
public class PostDTO {

    private long id;
    private String title,urlImage,description, tattooer;

    private PostType postType;
    public PostDTO(Post post) {

        this.id = post.getId();
        this.title = post.getTitle();
        this.urlImage = post.getUrlImage();
        this.description = post.getDescription();
        this.tattooer = post.getTattooer();
        this.postType = post.getPostType();
    }
}
