package com.inhouse.infrastructure.repository;

import com.inhouse.infrastructure.entity.LocationEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface LocationJpaRepository extends JpaRepository<LocationEntity, Integer> {

}
