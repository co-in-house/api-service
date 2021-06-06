package com.inhouse.domain.repository;

import java.util.ArrayList;

import com.inhouse.domain.object.Community;

/**
 * インフラ層とのインタフェース
 */
public interface CommunityRepository {

    ArrayList<Community> findByIdIn(ArrayList<Integer> idList);

    Community save(Community community);
}