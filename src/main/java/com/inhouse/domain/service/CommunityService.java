package com.inhouse.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inhouse.application.resource.community.GetCommunitiesListInputDto;
import com.inhouse.domain.object.Community;
import com.inhouse.domain.repository.CommunityRepository;
import com.inhouse.domain.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * ユーザ操作のロジック
 */
@Service
@RequiredArgsConstructor
public class CommunityService {

    @Autowired
    private TagRepository tagRepo;

    @Autowired
    private  CommunityRepository communityRepo;

    /**
     * 検索
     * @param inputDto
     * @return {@link Sample}型
     */
    public List<Community> getCommunityList(GetCommunitiesListInputDto input){
        if(Objects.isNull(input.getKeyword()) || input.getKeyword().equals("")){
            System.err.println("keyword is not available");
            // throw custom exception
        }
        // タグ名からコミュニティID一覧を取得
        ArrayList<Integer> idList = tagRepo.findCommunityidByTagnameContaining(input.getKeyword());
        System.out.println("コミュニティIDリスト: " + idList);
        // コミュニティID一覧からコミュニティを検索
        ArrayList<Community> communityList = communityRepo.findByIdIn(idList);
        System.out.println("コミュニティリスト: " + communityList);

        return communityList;

    }
}
