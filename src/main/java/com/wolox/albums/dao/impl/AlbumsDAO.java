package com.wolox.albums.dao.impl;

import com.wolox.albums.dao.IAlbumsDAO;
import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class AlbumsDAO implements IAlbumsDAO {

    @Value("${albums.url}")
    private String uri;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<User> listUsers() {
        return Arrays.asList(restTemplate.getForObject(uri + "/users", User[].class));
    }

    @Override
    public List<Photo> listPhotos() {
        return Arrays.asList(restTemplate.getForObject(uri+"/photos", Photo[].class));
    }
}
