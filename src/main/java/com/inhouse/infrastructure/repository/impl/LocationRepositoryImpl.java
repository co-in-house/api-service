package com.inhouse.infrastructure.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.inhouse.domain.object.Location;
import com.inhouse.domain.repository.LocationRepository;
import com.inhouse.infrastructure.entity.LocationEntity;
import com.inhouse.infrastructure.repository.LocationJpaRepository;

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
public class LocationRepositoryImpl implements LocationRepository {

    @Autowired
    private final LocationJpaRepository jpaRepo;

    @Override
    public List<Location> findAll() {
        List<Location> locations = new ArrayList<Location>();
        List<LocationEntity> entityList = jpaRepo.findAll();
        for (LocationEntity locationEntity : entityList) {
            locations.add(locationEntity.toDomain());
        }
        return locations;
    }

    
}