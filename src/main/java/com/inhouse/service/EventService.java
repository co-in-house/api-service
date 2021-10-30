package com.inhouse.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.inhouse.dao.EventDao;
import com.inhouse.dto.Event;
import com.inhouse.dto.EventMatrix;
import com.inhouse.util.ConsoleLogger;

@RequestScoped
public class EventService {
    
    @Inject
    private EventDao eventDao;

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     *  List内のcommunityIdに紐づく全てのイベントを取得する
     * @param communityIdList
     * @return {@link EventList}
     */
    public EventMatrix getEventMatrix(Long[] communityIdList){
        EventMatrix result = EventMatrix.builder().build();
        List<Event> eventList = new ArrayList<Event>();
        List<List<Event>> eventMatrix = new ArrayList<List<Event>>();

        try {
            // IN(communityIdList) order by start
            eventList.addAll(eventDao.getEventList(communityIdList));
            ConsoleLogger.debug("size : " + eventList.size());
            if(eventList.size() == 0){
                return result;
            }
            // sort 
            Date tmpStartDate =  DATE_FORMAT.parse(eventList.get(0).getStart());
            List<Event> eventRow = new ArrayList<Event>();
            for(int i = 0; i < eventList.size(); i++){
                if(DATE_FORMAT.parse(eventList.get(i).getStart()).compareTo(tmpStartDate) == 0){    
                    // 同じ日付
                    eventRow.add(eventList.get(i));
                } else{
                    // 異なる日付
                    eventMatrix.add(eventRow);
                    eventRow = new ArrayList<Event>();
                    tmpStartDate =  DATE_FORMAT.parse(eventList.get(i).getStart());
                    eventRow.add(eventList.get(i));
                }
                if(i == eventList.size() - 1){
                    // 終端処理
                    eventMatrix.add(eventRow);
                }
            }
            result.setEventMatrix(eventMatrix);
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

    /**
     * イベントを更新
     * @param event
     * @return boolean isSuccess
     */
    public boolean putEvent(Event event){
        boolean result = false;

        try {
            result = eventDao.putEvent(event);
        } catch (SQLException e) {
            ConsoleLogger.error(e.getMessage());
        }

        return result;
    }

    
}
