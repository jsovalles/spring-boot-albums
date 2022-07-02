package com.wolox.albums.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Data
class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(nullable = true)
    private int age;

}