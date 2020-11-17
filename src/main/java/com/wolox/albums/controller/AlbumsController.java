package com.wolox.albums.controller;

import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.users.User;
import com.wolox.albums.service.IAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v0/albums")
public class AlbumsController<T> {

    @Autowired
    IAlbumsService srv;

    @GetMapping("/users")
    public List<User> listUsers(){

        List<User> users = srv.listUsers();

        return users;
    }

    @GetMapping("/photos")
    public List<Photo> listPhotos(){

        List<Photo> photos = srv.listPhotos();

        return photos;

    }
}
