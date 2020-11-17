package com.wolox.albums.dao.impl;

import com.wolox.albums.dao.IAlbumsDAO;
import com.wolox.albums.dao.templates.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class AlbumsDAO implements IAlbumsDAO {

    final String uri = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<User> listUsers() {
        return Arrays.asList(restTemplate.getForObject(uri, User[].class));
    }
}
