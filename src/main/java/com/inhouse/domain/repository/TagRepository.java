package com.inhouse.domain.repository;

import java.util.ArrayList;

import com.inhouse.domain.object.Tag;

/**
 * インフラ層とのインタフェース
 */
public interface TagRepository {

    ArrayList<Integer> findCommunityidByTagnameContaining(String keyword);

    Tag save(Tag tag);


}