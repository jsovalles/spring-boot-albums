package com.wolox.albums.service;

import com.wolox.albums.dao.templates.users.User;

import java.util.List;

public interface IAlbumsService {
    List<User> listUsers();
}