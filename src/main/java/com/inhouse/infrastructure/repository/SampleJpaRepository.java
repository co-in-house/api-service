package com.inhouse.infrastructure.repository;

import com.inhouse.infrastructure.entity.SampleEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface SampleJpaRepository extends JpaRepository<SampleEntity, String> {

}
