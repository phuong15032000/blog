package com.trandiepphuong.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

//    @OneToMany(mappedBy = "tag")
//    private List<Post_tag> postTagList;
    @ManyToMany(mappedBy = "tag")
    private List<Post> postList;
}
