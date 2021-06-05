package com.inhouse.application.resource.community;

import java.util.List;

import com.inhouse.domain.object.Community;

import lombok.Data;

@Data
public class GetCommunitiesListOutputDto {
 
    
    private List<Community> communityList;

}
