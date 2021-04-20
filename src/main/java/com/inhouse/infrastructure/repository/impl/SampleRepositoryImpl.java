package com.inhouse.infrastructure.repository.impl;

import java.util.Optional;

import com.inhouse.domain.object.Sample;
import com.inhouse.domain.repository.SampleRepository;
import com.inhouse.infrastructure.entity.SampleEntity;
import com.inhouse.infrastructure.repository.SampleJpaRepository;

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
public class SampleRepositoryImpl implements SampleRepository {

    @Autowired
    private final SampleJpaRepository jpaRepo;

    @Override
    public Sample save(Sample sample) {
        Sample response = jpaRepo.save(SampleEntity.build(sample)).toDomain();
        return response;
    }

    @Override
    public Optional<Sample> findByName(String name) {
        Optional<SampleEntity> entity = jpaRepo.findById(name);
        if(entity.isPresent()){
            return Optional.of(entity.get().toDomain());
        } else {
            return Optional.empty();
        }
    }
}