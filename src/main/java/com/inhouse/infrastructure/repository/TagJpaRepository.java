package com.inhouse.infrastructure.repository;

import java.util.List;

import com.inhouse.infrastructure.entity.TagEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JPAを利用するためのインタフェース
 */
public interface TagJpaRepository extends JpaRepository<TagEntity, Integer> {
    
    @Query(value = "select distinct(community_id) from tag_info where tag_name like %?1% ", nativeQuery = true)
    List<Integer> findCommunityidByTagnameContaining(String keyword);

}
