package com.wolox.albums.service.impl;

import com.wolox.albums.dao.IAlbumsDAO;
import com.wolox.albums.dao.IPermissionsDAO;
import com.wolox.albums.dao.entity.Permission;
import com.wolox.albums.dao.templates.albums.Album;
import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.posts.Comment;
import com.wolox.albums.dao.templates.posts.Post;
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
    IPermissionsDAO permissionsDAO;

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

        permissionsDAO.save(permission);
    }

    @Override
    public void updateUserPermissions(Permission permission) {

        logic.updateUserPermissionBusinessLogic(permission);

        permissionsDAO.save(permission);
    }

    @Override
    public List<User> listUsersWithAlbumPermissions(int albumId, String role) {

        List<Permission> list = permissionsDAO.findAllUsersByAlbumIdAndRole(albumId, role);

        logic.listUsersWithAlbumPermissionsBusinessLogic(list);

        List<User> users = new ArrayList<>();

        list.forEach(permission -> {
            User user = albumsDAO.getUser(permission.getUserId());
            users.add(user);
        });

        return users;
    }

    @Override
    public List<Comment> listUserComments(String name, String userId) {
        if (name != null)
            return albumsDAO.getCommentsByName(name);
        else {
            List<Post> postsFromUser = albumsDAO.getUserPosts(userId);

            List<Comment> comments = new ArrayList<>();

            postsFromUser.forEach(post -> {
                Comment comment = albumsDAO.getCommentsByPostId(post.getId());
                comments.add(comment);
            });
            return comments;
        }

    }
}
