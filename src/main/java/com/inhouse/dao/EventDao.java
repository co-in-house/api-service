package com.inhouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.inhouse.dto.Event;
import com.inhouse.util.ConsoleLogger;

@Dependent
public class EventDao {
    
    @Inject
    private Connection con;

    /** イベントリストを取得するSQL */
    private static final String SQL_SELECT_EVENT_LIST = "SELECT community_id, event_id, title, start, end, location, description, thumbnail_img FROM event WHERE delete_flag = 0 and community_id = ?";

    /** イベントを登録するSQL */
    private static final String SQL_INSERT_EVENT = "INSERT INTO event(COMMUNITY_ID,TITLE,START,END,LOCATION,DESCRIPTION,THUMBNAIL_IMG,CREATED_AT,MODIFIED_AT) VALUES (?,?,?,?,?,?,?,?,?)";

    public List<Event> getEventList(Long communityId) throws SQLException {
        List<Event> result = new ArrayList<Event>();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELECT_EVENT_LIST)){
            pst.setLong (1,communityId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELECT_EVENT_LIST + ", param : " + communityId.toString());
            try (ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    result.add(
                        Event.builder()
                            .communityId(rs.getLong("community_id"))
                            .eventId(rs.getLong("event_id"))
                            .title(rs.getString("title"))
                            .start(rs.getTimestamp("start").toString())
                            .end(rs.getTimestamp("end").toString())
                            .location(rs.getString("location"))
                            .description(rs.getString("description"))
                            .thumbnailImg(rs.getString("thumbnail_img"))
                        .build()
                    );
                }
            }
        }
        return result;
    }


    public boolean postEvent(Event event) throws SQLException {
        Timestamp currentTimestamp =  Timestamp.valueOf(LocalDateTime.now());

        try (PreparedStatement pst = con.prepareStatement(SQL_INSERT_EVENT)){
            pst.setLong (1,event.getCommunityId());
            pst.setString(2, event.getTitle());
            pst.setTimestamp(3, Timestamp.valueOf(event.getStart()));
            pst.setTimestamp(4, Timestamp.valueOf(event.getEnd()));
            pst.setString(5, event.getLocation());
            pst.setString(6, event.getDescription());
            pst.setString(7, event.getThumbnailImg());
            pst.setTimestamp(8, currentTimestamp);
            pst.setTimestamp(9, currentTimestamp);
            ConsoleLogger.debug(" RUN SQL : " + SQL_INSERT_EVENT + ", param : " + event.toString());
            int result = pst.executeUpdate();
            if(result != 1){
                ConsoleLogger.error("インサート結果が一件ではありません");
                return false;
            }
        }
        return true;
    }

}