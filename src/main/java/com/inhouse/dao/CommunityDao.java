package com.inhouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.inhouse.dto.Community;
import com.inhouse.util.ConsoleLogger;

@Dependent
public class CommunityDao {
    
    @Inject
    private Connection con;

    /**
     * 削除されていないコミュニティのID検索
     */
    private static final String SQL_SELETE_COMMUNITY = "SELECT community_id, community_name, icon_img FROM community WHERE delete_flag = 0 and community_id = ?";



    public Community getCommunityById(Long communityId) throws SQLException {
        Community result = Community.builder().build();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELETE_COMMUNITY)){
            pst.setLong (1,communityId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELETE_COMMUNITY + ", param : " + communityId.toString());
            try (ResultSet rs = pst.executeQuery()){
                rs.next();
                result = Community.builder()
                                    .communityId(communityId)
                                    .communityName(rs.getString("community_name"))
                                    .iconImg(rs.getString("icon_img"))
                                    .build();
            }
        }
        return result;
    }
}


