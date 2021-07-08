package com.trandiepphuong.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToMany(mappedBy = "user")
    private List<Role> roleList;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "mobile")
    String mobile;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "intro")
    String intro;
}
