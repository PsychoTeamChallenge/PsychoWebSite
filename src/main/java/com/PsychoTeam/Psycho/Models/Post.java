package com.PsychoTeam.Psycho.Models;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    private String title,urlImage,description, tattooer;

    private int fires;
    private PostType postType;

    public Post() {}

    public Post(Client client, String title, String urlImage, String description, String tattooer, PostType postType, int fires) {
        this.client = client;
        this.title = title;
        this.urlImage = urlImage;
        this.description = description;
        this.tattooer = tattooer;
        this.postType = postType;
        this.fires = fires;
    }
}
