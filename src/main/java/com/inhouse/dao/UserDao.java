package com.inhouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.inhouse.dto.UserInfo;
import com.inhouse.util.ConsoleLogger;

@Dependent
public class UserDao {
    
    @Inject
    private Connection con;

    /**
     * 所属するコミュニティのID検索
     */
    private static final String SQL_SELECT_JOINED_COMMUNITY_ID = "SELECT community_id FROM user_community_relation WHERE user_id = ?";
    
    
    private static final String SQL_SELECT_JOINER_USER_LIST = "SELECT user.user_id, user.user_name,user.icon_img FROM user_community_relation as ucr"
    + " INNER JOIN user as user ON ucr.user_id =user.user_id"
    + " where ucr.community_id = ?";

    public ArrayList<Long> getJoinedCommunityIdListByUserId(Long userId) throws SQLException {
        ArrayList<Long> result = new ArrayList<Long>();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELECT_JOINED_COMMUNITY_ID)){
            pst.setLong (1,userId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELECT_JOINED_COMMUNITY_ID + ", param : " + userId.toString());
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    result.add(rs.getLong("community_id"));
                }
            }
        }
        return result;
    }

    public ArrayList<UserInfo> getUserInfoListByCommunityId(Long communityId) throws SQLException {
        ArrayList<UserInfo> result = new ArrayList<UserInfo>();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELECT_JOINER_USER_LIST)){
            pst.setLong (1,communityId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELECT_JOINER_USER_LIST + ", param : " + communityId.toString());
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    result.add(UserInfo.builder()
                                        .userId(rs.getLong("user_id"))
                                        .userName(rs.getString("user_name"))
                                        .iconImg(rs.getString("icon_img"))
                                .build()
                    );
                }
            }
        }
        return result;
    }
}


