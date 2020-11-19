package com.wolox.albums.dao;

import com.wolox.albums.dao.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPermissionsDAO extends JpaRepository<Permission, Integer> {

    boolean existsByUserIdAndAlbumId(int userId, int albumId);

    //List<Permission> findAllUsersByAlbumIdAndRoleId(int albumId, int roleId);

    @Query(value = "SELECT * FROM permissions p, roles r WHERE p.album_id = ?1  AND r.role_type = ?2", nativeQuery = true)
    List<Permission> findAllUsersByAlbumIdAndRole(int albumId, String role);
}
