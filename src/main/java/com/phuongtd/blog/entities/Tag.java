package com.phuongtd.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
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

    @ManyToMany(mappedBy = "tagList")
    private List<Post> postList;
}
