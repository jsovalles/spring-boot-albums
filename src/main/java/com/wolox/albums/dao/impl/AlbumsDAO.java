package com.wolox.albums.dao.impl;

import com.wolox.albums.dao.IAlbumsDAO;
import com.wolox.albums.dao.templates.albums.Album;
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

    @Override
    public List<Album> listAlbums() {
        return Arrays.asList(restTemplate.getForObject(uri+"/albums", Album[].class));
    }

    @Override
    public List<Album> listAlbumsFromUser(String userId) {
        return Arrays.asList(restTemplate.getForObject(uri+"/users/" + userId + "/albums", Album[].class));
    }

    @Override
    public List<Photo> getPhotoFromUser(int id) {
        return Arrays.asList(restTemplate.getForObject(uri+"/photos?id=" + id, Photo[].class));
    }

    @Override
    public User getUser(int userId) {
        return restTemplate.getForObject(uri + "/users/" + userId, User.class);
    }
}
