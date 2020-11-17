package com.wolox.albums.dao.templates.photos;

import lombok.Data;

@Data
public class Photo {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
