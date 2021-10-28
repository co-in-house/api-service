package com.inhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.inhouse.dao.EventDao;
import com.inhouse.dto.Event;
import com.inhouse.dto.EventList;
import com.inhouse.util.ConsoleLogger;

@RequestScoped
public class EventService {
    
    @Inject
    private EventDao eventDao;
    
    /**
     *  List内のcommunityIdに紐づく全てのイベントを取得する
     * @param communityIdList
     * @return {@link EventList}
     */
    public EventList getEventList(ArrayList<Long> communityIdList){
        EventList result = EventList.builder().build();
        List<Event> eventList = new ArrayList<Event>();
        try {
            for (Long communityId : communityIdList) {        
                eventList.addAll(eventDao.getEventList(communityId));
            }
            result.setEventList(eventList);
        } catch (Exception e) {
            ConsoleLogger.error(e.getMessage());
        }
        return result;
    }

    /**
     * イベントを登録
     * @param event
     * @return boolean isSuccess
     */
    public boolean postEvent(Event event){
        boolean result = false;

        try {
            result = eventDao.postEvent(event);
        } catch (SQLException e) {
            ConsoleLogger.error(e.getMessage());
        }

        return result;
    }
}
