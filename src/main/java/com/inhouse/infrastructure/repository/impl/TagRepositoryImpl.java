package com.inhouse.infrastructure.repository.impl;

import java.util.ArrayList;

import com.inhouse.domain.repository.TagRepository;
import com.inhouse.infrastructure.repository.TagJpaRepository;

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
public class TagRepositoryImpl implements TagRepository {

    @Autowired
    private final TagJpaRepository jpaRepo;

    @Override
    public ArrayList<Integer> findCommunityidByTagnameContaining(String keyword) {
        return (ArrayList<Integer>) jpaRepo.findCommunityidByTagnameContaining(keyword);
    }

}