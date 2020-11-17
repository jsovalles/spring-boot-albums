package com.wolox.albums.service.impl;

import com.wolox.albums.dao.IAlbumsDAO;
import com.wolox.albums.dao.templates.photos.Photo;
import com.wolox.albums.dao.templates.users.User;
import com.wolox.albums.service.IAlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
