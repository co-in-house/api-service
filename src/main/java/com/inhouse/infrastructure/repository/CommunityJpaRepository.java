package com.inhouse.infrastructure.repository;

import java.util.List;

import com.inhouse.infrastructure.entity.CommunityEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JPAを利用するためのインタフェース
 */
public interface CommunityJpaRepository extends JpaRepository<CommunityEntity, Integer> {
    
    @Query(value = "select * from community_info where community_id in :idList", nativeQuery = true) 
    List<CommunityEntity> findByIdIn(List<Integer> idList);

}
