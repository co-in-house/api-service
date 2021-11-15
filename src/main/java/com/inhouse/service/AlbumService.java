package com.inhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.inhouse.dao.AlbumDao;
import com.inhouse.dto.Album;
import com.inhouse.dto.Photo;
import com.inhouse.util.ConsoleLogger;

@RequestScoped
public class AlbumService {
    
    @Inject
    private AlbumDao albumDao;


    /**
     *  communityIdに紐づくアルバム一覧を取得する
     * @param communityId
     * @return {@link Album}型のList
     */
    public List<Album> getAlbumListByCommunityId(Long communityId){
        List<Album> result = new ArrayList<Album>();
        
        try {
            result = albumDao.getAlbumListByCommunityId(communityId);
        } catch (SQLException e) {
            ConsoleLogger.error(e.getMessage());
        }

        return result;
    }

    /**
     *  albumIdに紐づく画像一覧を取得する
     * @param albumId
     * @return {@link Photo}型のList
     */
    public List<Photo> getPhotoListByAlbumId(Long albumId){
        List<Photo> result = new ArrayList<Photo>();
        
        try {
            result = albumDao.getPhotoListByAlbumId(albumId);
        } catch (SQLException e) {
            ConsoleLogger.error(e.getMessage());
        }

        return result;
        
    }
}
