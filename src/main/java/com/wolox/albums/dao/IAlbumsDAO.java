package com.wolox.albums.dao;

import com.wolox.albums.dao.templates.albums.Album;
import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.posts.Comment;
import com.wolox.albums.dao.templates.posts.Post;
import com.wolox.albums.dao.templates.users.User;

import java.util.List;

public interface IAlbumsDAO {

    List<User> listUsers();

    List<Photo> listPhotos();

    List<Album> listAlbums();

    List<Album> listAlbumsFromUser(String userId);

    List<Photo> getPhotoFromUser(int id);

    User getUser(int userId);

    List<Comment> getCommentsByName(String name);

    List<Post> getUserPosts(String userId);

    Comment getCommentsByPostId(int id);
}
