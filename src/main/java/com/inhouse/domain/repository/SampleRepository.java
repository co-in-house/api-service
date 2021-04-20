package com.inhouse.domain.repository;

import java.util.Optional;

import com.inhouse.domain.object.Sample;

/**
 * インフラ層とのインタフェース
 */
public interface SampleRepository {

    /**
     * 作成、更新
     *
     * @param Sample 作成、更新したsample
     * @return 更新後のsample
     */
    Sample save(Sample sample);

    Optional<Sample> findByName(String name);
}