package com.wolox.albums.dao;

import com.wolox.albums.dao.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionsDAO extends JpaRepository<Permission, Integer> {

    boolean existsByUserIdAndAlbumId(int userId, int albumId);
}
