package com.wolox.albums.service;

import com.wolox.albums.dao.entity.Permission;
import com.wolox.albums.dao.templates.albums.Album;
import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.users.User;

import java.util.List;

public interface IAlbumsService {
    List<User> listUsers();

    List<Photo> listPhotos();

    List<Album> listAlbums();

    List<Album> listAlbumsFromUser(String userId);

    List<Photo> listPhotosFromUser(String userId);

    void createUserPermissions(Permission permission);
}
