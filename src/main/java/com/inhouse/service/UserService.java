package com.inhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.inhouse.dao.UserDao;
import com.inhouse.dto.UserInfo;
import com.inhouse.util.ConsoleLogger;

@RequestScoped
public class UserService {
 
    @Inject
    private UserDao userDao;


    /**
     *  communityIdに紐づく参加ユーザ一覧を取得する
     * @param communityId
     * @return {@link UserInfo}型のList
     */
    public List<UserInfo> getUserInfoListByCommunityId(Long communityId){
        List<UserInfo> result = new ArrayList<UserInfo>();
        
        try {
            result = userDao.getUserInfoListByCommunityId(communityId);
        } catch (SQLException e) {
            ConsoleLogger.error(e.getMessage());
        }

        return result;
    }
}
