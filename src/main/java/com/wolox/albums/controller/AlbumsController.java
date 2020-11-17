package com.wolox.albums.controller;

import com.wolox.albums.dao.templates.users.User;
import com.wolox.albums.service.IAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumsController<T> {

    @Autowired
    IAlbumsService srv;

    @GetMapping
    public List<User> getUsers(){

        List<User> users = srv.listUsers();

        return users;
    }
}
