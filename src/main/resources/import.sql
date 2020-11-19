/* Populate table roles */
INSERT INTO roles (role_type) VALUES('READ_ONLY');
INSERT INTO roles (role_type) VALUES('READ_WRITE');

/* Populate table permissions */
INSERT INTO permissions (role_id, user_id, album_id) VALUES(1, 1, 24);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(1, 3, 24);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(1, 4, 24);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(1, 5, 24);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(1, 6, 24);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(1, 7, 24);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(2, 1, 31);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(1, 2, 56);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(2, 1, 42);
INSERT INTO permissions (role_id, user_id, album_id) VALUES(2, 3, 85);


