package com.inhouse.domain.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inhouse.domain.object.Community;
import com.inhouse.domain.object.Tag;
import com.inhouse.domain.repository.CommunityRepository;
import com.inhouse.domain.repository.TagRepository;
import com.inhouse.util.CommonConst;

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

    private final Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

    /**
     * 検索
     * @param input
     * @return {@link Community}型のList
     */
    public List<Community> getCommunityList(String input){
        if(Objects.isNull(input) || input.equals("")){
            System.err.println("keyword is not available");
            // throw custom exception
        }
        // タグ名からコミュニティID一覧を取得
        ArrayList<Integer> idList = tagRepo.findCommunityidByTagnameContaining(input);
        System.out.println("コミュニティIDリスト: " + idList);
        // コミュニティID一覧からコミュニティを検索
        ArrayList<Community> communityList = communityRepo.findByIdIn(idList);
        System.out.println("コミュニティリスト: " + communityList);

        return communityList;

    }

    /**
     * 作成
     * @param input
     * @return {@link int}型
     */
    public int createCommunity(Community input){
        input.setCreated(currentTimestamp);
        input.setModified(currentTimestamp);
        Community community = communityRepo.save(input);


        List<Tag> tagList = input.getTagList();
        tagList.add(Tag.builder()
                        .communityId(community.getCommunityId())
                        .tagName(community.getCommunityName())
                        .tagType(CommonConst.TAG_TYPE_ROOT)
                        .build()
                    );
        for (Tag tag : tagList) {
            tag.setCommunityId(community.getCommunityId());
            tagRepo.save(tag);
        }
        return community.getCommunityId();
    }
}
