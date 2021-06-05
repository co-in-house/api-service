package com.inhouse.infrastructure.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inhouse.domain.object.Community;
import com.inhouse.domain.repository.CommunityRepository;
import com.inhouse.infrastructure.entity.CommunityEntity;
import com.inhouse.infrastructure.repository.CommunityJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * 永続化の実装クラス
 * ドメインオブジェクトをEntityに変換してJPAをラップする
 */
@Repository
@Transactional
@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepository {

    @Autowired
    private final CommunityJpaRepository jpaRepo;

    @Override
    public ArrayList<Community> findByIdIn(ArrayList<Integer> idList) {
        ArrayList<Community> response = new ArrayList<Community>();
        List<CommunityEntity> resultSet = jpaRepo.findByIdIn(idList);
        if(!Objects.isNull(resultSet) && resultSet.size() > 0 ){
            for (CommunityEntity communityEntity : resultSet) {
                response.add(communityEntity.toDomain());
            }
        }
        return response;
    }

}