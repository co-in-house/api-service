package com.inhouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private static final String SQL_SELECT_COMMUNITY = "SELECT community_id, community_name, icon_img FROM community WHERE delete_flag = 0 and community_id = ?";

    private static final String SQL_SELECT_COMMUNITY_LIKE_TITE = "SELECT community_id, community_name, location, description, icon_img FROM community WHERE COMMUNITY_NAME LIKE ?";



    public Community getCommunityById(Long communityId) throws SQLException {
        Community result = Community.builder().build();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELECT_COMMUNITY)){
            pst.setLong(1,communityId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELECT_COMMUNITY + ", param : " + communityId.toString());
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

    public ArrayList<Community> getCommunityListLikeTitle(String keyword) throws SQLException {
        ArrayList<Community> result = new ArrayList<Community>();
        try(PreparedStatement pst = con.prepareStatement(SQL_SELECT_COMMUNITY_LIKE_TITE)){
            pst.setString(1, "%" + keyword.trim() + "%"); // 部分一致
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELECT_COMMUNITY_LIKE_TITE + ", param : " + keyword.trim());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                result.add(Community.builder()
                            .communityId(rs.getLong("community_id"))
                            .communityName(rs.getString("community_name"))
                            .description(rs.getString("description"))
                            .location(rs.getString("location"))
                            .iconImg(rs.getString("icon_img"))
                            .build() 
                );
            }
        }
        return result;
    }
}


