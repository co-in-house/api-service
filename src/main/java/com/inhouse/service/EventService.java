package com.inhouse.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.inhouse.dao.EventDao;
import com.inhouse.dto.Event;
import com.inhouse.dto.EventList;

@RequestScoped
public class EventService {
    
    @Inject
    private EventDao eventDao;
    
    public EventList getEventList(String communityIds){
        EventList result = EventList.builder().build();
        List<Event> eventList = new ArrayList<Event>();
        try {
            for (String str : communityIds.split(",")) {        
                eventList.addAll(eventDao.getEventList(Long.parseLong(str)));
            }
            result.setEventList(eventList);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }
}
