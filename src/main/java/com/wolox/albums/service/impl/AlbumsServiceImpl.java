package com.wolox.albums.service.impl;

import com.wolox.albums.dao.IAlbumsDAO;
import com.wolox.albums.dao.templates.albums.Album;
import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.users.User;
import com.wolox.albums.service.IAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumsServiceImpl implements IAlbumsService {

    @Autowired
    IAlbumsDAO dao;

    @Override
    public List<User> listUsers() {
        return dao.listUsers();
    }

    @Override
    public List<Photo> listPhotos() {
        return dao.listPhotos();
    }

    @Override
    public List<Album> listAlbums() {
        return dao.listAlbums();
    }

    @Override
    public List<Album> listAlbumsFromUser(String userId) {
        return dao.listAlbumsFromUser(userId);
    }

    @Override
    public List<Photo> listPhotosFromUser(String userId) {

        List<Album> albums = dao.listAlbumsFromUser(userId);

        List<Photo> photosFromUser = new ArrayList<>();

        albums.forEach(album -> {
            Photo photo = dao.getPhotoFromUser(album.getId()).get(0);
            photosFromUser.add(photo);
        });

        return photosFromUser;
    }
}
