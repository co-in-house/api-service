package com.inhouse.domain.repository;

import java.util.ArrayList;

/**
 * インフラ層とのインタフェース
 */
public interface TagRepository {

    ArrayList<Integer> findCommunityidByTagnameContaining(String keyword);

// //     /**
// //      * 作成、更新
// //      *
// //      * @param Sample 作成、更新したsample
// //      * @return 更新後のsample
// //      */
// //     Sample save(Sample sample);

// //     Optional<Sample> findByName(String name);

}