package com.inhouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    /**
     * イベントリストを取得するSQL
     */
    private static final String SQL_SELETE_EVENT_LIST = "SELECT";

    public List<Event> getEventList(Long communityId) throws SQLException {
        List<Event> result = new ArrayList<Event>();
        try (PreparedStatement pst = con.prepareStatement(SQL_SELETE_EVENT_LIST)){
            pst.setLong (1,communityId);
            ConsoleLogger.debug(" RUN SQL : " + SQL_SELETE_EVENT_LIST + ", param : " + communityId.toString());
            try (ResultSet rs = pst.executeQuery()){
                rs.next();
                result.add(
                    Event.builder()
                        .communityId(rs.getLong("event_id"))
                        .eventId(rs.getLong("event_id"))
                        .title(rs.getString("title"))
                        .start(rs.getTimestamp("start").toString())
                        .end(rs.getTimestamp("end").toString())
                        .locaiton(rs.getString("location"))
                        .description(rs.getString("description"))
                        .thumbnailImg(rs.getString("thumbnail_img"))
                    .build()
                );
            }
        }
        return result;
    }
}
