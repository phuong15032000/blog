package com.trandiepphuong.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

//    @OneToMany(mappedBy = "post")
//    private List<Post_tag> postTagList;

    @ManyToMany(mappedBy = "post")
    private List<Tag> tagList;

    @Column(name = "title")
    String column;

    @Column(name = "created_at")
    Date createdAt;

    @Column(name = "updated_at")
    Date updatedAt;

    @Column(name = "content")
    String content;
}
