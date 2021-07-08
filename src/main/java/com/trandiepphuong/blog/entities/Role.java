package com.trandiepphuong.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name="role")
    String role;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
