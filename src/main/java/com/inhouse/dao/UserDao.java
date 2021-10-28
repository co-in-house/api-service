package com.inhouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.inhouse.util.ConsoleLogger;

@Dependent
public class UserDao {
    
    @Inject
    private Connection con;

    /**
     * 削除されていないコミュニティのID検索
     */
    private static final String SQL_SELETE_JOINED_COMMUNITY_ID = "SELECT community_id FROM user_community_relation WHERE user_id = ?";



    public ArrayList<Long> getJoinedCommunityIdListByUserId(Long userId) throws SQLException {
        ArrayList<Long> result = new ArrayList<Long>();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELETE_JOINED_COMMUNITY_ID)){
            pst.setLong (1,userId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELETE_JOINED_COMMUNITY_ID + ", param : " + userId.toString());
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    result.add(rs.getLong("community_id"));
                }
            }
        }
        return result;
    }
}


