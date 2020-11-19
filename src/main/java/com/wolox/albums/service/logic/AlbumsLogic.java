package com.wolox.albums.service.logic;

import com.wolox.albums.dao.IAlbumsDAO;
import com.wolox.albums.dao.IPermissionsDAO;
import com.wolox.albums.dao.entity.Permission;
import com.wolox.albums.dao.templates.albums.Album;
import com.wolox.albums.utils.AlbumsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumsLogic {

    @Autowired
    IPermissionsDAO permissionsDAO;

    @Autowired
    IAlbumsDAO albumsDAO;

    public void createUserPermissionBusinessLogic(Permission permission) {

        List<Album> albumsList = albumsDAO.listAlbumsFromUser(String.valueOf(permission.getUserId()));

        if (albumsList.stream().anyMatch(album -> album.getId() == permission.getAlbumId()))
            throw new AlbumsException("The current Album is already registered to the user");

        if (permissionsDAO.existsByUserIdAndAlbumId(permission.getUserId(), permission.getAlbumId()))
            throw new AlbumsException("The current permission between the album and the user already exist");
    }

    public void updateUserPermissionBusinessLogic(Permission permission) {

        if (!permissionsDAO.existsByUserIdAndAlbumId(permission.getUserId(),permission.getAlbumId()))
            throw new AlbumsException("The current Role for the Album and user doesn't exist");

    }

    public void listUsersWithAlbumPermissionsBusinessLogic(List<Permission> list) {

        if (list==null || list.isEmpty())
            throw new AlbumsException("The current Album or role doesn't have associated users");

    }
}
