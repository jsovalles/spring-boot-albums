package com.wolox.albums.controller;

import com.wolox.albums.dao.entity.Permission;
import com.wolox.albums.dao.templates.albums.Album;
import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.posts.Comment;
import com.wolox.albums.dao.templates.users.User;
import com.wolox.albums.service.IAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/v0")
public class AlbumsController<T> {

    @Autowired
    IAlbumsService srv;

    @GetMapping("/users")
    public List<User> listUsers() {

        List<User> users = srv.listUsers();

        return users;
    }

    @GetMapping("/photos")
    public List<Photo> listPhotos() {

        List<Photo> photos = srv.listPhotos();

        return photos;

    }

    @GetMapping("/albums")
    public List<Album> listAlbums() {

        List<Album> albums = srv.listAlbums();

        return albums;
    }

    @GetMapping("/users/{user-id}/albums")
    public List<Album> listAlbumsFromUser(@PathVariable(name = "user-id") String userId) {

        List<Album> albums = srv.listAlbumsFromUser(userId);

        return albums;
    }

    @GetMapping("/users/{user-id}/photos")
    public List<Photo> listPhotosFromUser(@PathVariable(name = "user-id") String userId) {

        List<Photo> photo = srv.listPhotosFromUser(userId);

        return photo;
    }

    @PostMapping("/permissions")
    public void createUserPermissions(@RequestBody Permission permission) {
        srv.createUserPermissions(permission);
    }

    @PutMapping("/permissions")
    public void updateUserPermissions(@RequestBody Permission permission) {

        srv.updateUserPermissions(permission);
    }

    @GetMapping("/permissions/albums/{album-id}")
    public List<User> listUsersWithAlbumPermissions(@PathVariable(name = "album-id") int albumId, @RequestParam(name = "role", required = false) String role){

        List<User> users = srv.listUsersWithAlbumPermissions(albumId, role);

        return users;

    }

    @GetMapping("/comments")
    public List<Comment> listUserComments(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "userId", required = false) String userId){

        List<Comment> comments = srv.listUserComments(name,userId);

        return comments;

    }
}
