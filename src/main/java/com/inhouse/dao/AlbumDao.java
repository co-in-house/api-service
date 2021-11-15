package com.inhouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.inhouse.dto.Album;
import com.inhouse.dto.Photo;
import com.inhouse.util.ConsoleLogger;

@Dependent
public class AlbumDao {
       
    @Inject
    private Connection con;

    private static final String SQL_SELECT_ALBUM_LIST = "SELECT album_id, album_name, thumbnail_img, created_at FROM album WHERE community_id = ?";

    private static final String SQL_SELECT_PHOTO_LIST_BY_ALBUM_ID = "SELECT url, created_at FROM album_photo WHERE album_id = ?";

    public ArrayList<Album> getAlbumListByCommunityId(Long communityId) throws SQLException {
        ArrayList<Album> result = new ArrayList<Album>();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELECT_ALBUM_LIST)){
            pst.setLong (1,communityId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELECT_ALBUM_LIST + ", param : " + communityId.toString());
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    result.add(Album.builder()
                                        .albumId(rs.getLong("album_id"))
                                        .albumName(rs.getString("album_name"))
                                        .thumbnailImg(rs.getString("thumbnail_img"))
                                        .createdAt(rs.getTimestamp("created_at").toString())
                                .build()
                    );
                }
            }
        }
        return result;
    }


    public ArrayList<Photo> getPhotoListByAlbumId(Long albumId) throws SQLException {
        ArrayList<Photo> result = new ArrayList<Photo>();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELECT_PHOTO_LIST_BY_ALBUM_ID)){
            pst.setLong (1,albumId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELECT_PHOTO_LIST_BY_ALBUM_ID + ", param : " + albumId.toString());
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    result.add(Photo.builder()
                                        .url(rs.getString("url"))
                                        .createdAt(rs.getTimestamp("created_at").toString())
                                .build()
                    );
                }
            }
        }
        return result;
    }
}
