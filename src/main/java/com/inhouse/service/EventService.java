package com.inhouse.service;

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
}
