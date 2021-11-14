package com.inhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.inhouse.dao.CommunityDao;
import com.inhouse.dao.UserDao;
import com.inhouse.dto.Community;
import com.inhouse.dto.CommunityList;
import com.inhouse.util.ConsoleLogger;

@RequestScoped
public class CommunityService {

    @Inject
    private UserDao userDao;

    @Inject
    private CommunityDao comDao;

    /**
     * 
     * @param userId
     * @return {@link CommunityList} 
     */
    public CommunityList getCommunityListByUserId(Long userId){
        ArrayList<Community> communityList = new ArrayList<Community>();

        ArrayList<Long> joineCommunityIdList;
        try {
            joineCommunityIdList = userDao.getJoinedCommunityIdListByUserId(userId);
            for (Long communityId : joineCommunityIdList) {        
                communityList.add(comDao.getCommunityById(communityId));
            }
        } catch (SQLException e) {
            ConsoleLogger.error(e.getMessage());
        }
   
        return CommunityList.builder().communityList(communityList).build();
    }


    /**
     * 
     * @param keyword
     * @return {@link CommunityList} 
     */
    public CommunityList getCommunityListLikeKeyword(String keyword){
        ArrayList<Community> communityList = new ArrayList<Community>();

        try {
            communityList = comDao.getCommunityListLikeTitle(keyword);
        } catch (SQLException e) {
            ConsoleLogger.error(e.getMessage());
        }
   
        return CommunityList.builder().communityList(communityList).build();
    }
}
