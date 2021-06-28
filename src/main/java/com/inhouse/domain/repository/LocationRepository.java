package com.inhouse.domain.repository;

import java.util.List;

import com.inhouse.domain.object.Location;

/**
 * インフラ層とのインタフェース
 */
public interface LocationRepository {

    List<Location> findAll();
}