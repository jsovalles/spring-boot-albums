package com.wolox.albums.service.impl;

import com.wolox.albums.dao.IAlbumsDAO;
import com.wolox.albums.dao.IPermissionsDAO;
import com.wolox.albums.dao.entity.Permission;
import com.wolox.albums.dao.templates.albums.Album;
import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.users.User;
import com.wolox.albums.service.IAlbumsService;
import com.wolox.albums.service.logic.AlbumsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumsServiceImpl implements IAlbumsService {

    private static final Logger log = LoggerFactory.getLogger(AlbumsServiceImpl.class);

    @Autowired
    IAlbumsDAO albumsDAO;

    @Autowired
    IPermissionsDAO rolesDAO;

    @Autowired
    AlbumsLogic logic;

    @Override
    public List<User> listUsers() {
        return albumsDAO.listUsers();
    }

    @Override
    public List<Photo> listPhotos() {
        return albumsDAO.listPhotos();
    }

    @Override
    public List<Album> listAlbums() {
        return albumsDAO.listAlbums();
    }

    @Override
    public List<Album> listAlbumsFromUser(String userId) {
        return albumsDAO.listAlbumsFromUser(userId);
    }

    @Override
    public List<Photo> listPhotosFromUser(String userId) {

        List<Album> albums = albumsDAO.listAlbumsFromUser(userId);

        List<Photo> photosFromUser = new ArrayList<>();

        albums.forEach(album -> {
            Photo photo = albumsDAO.getPhotoFromUser(album.getId()).get(0);
            photosFromUser.add(photo);
        });

        return photosFromUser;
    }

    @Override
    public void createUserPermissions(Permission permission) {

        logic.createUserPermissionBusinessLogic(permission);

        rolesDAO.save(permission);
    }

    @Override
    public void updateUserPermissions(Permission permission) {

        logic.updateUserPermissionBusinessLogic(permission);

        rolesDAO.save(permission);
    }
}
