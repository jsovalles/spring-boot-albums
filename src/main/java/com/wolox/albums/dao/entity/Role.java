package com.wolox.albums.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_type")
    private String roleType;

}
