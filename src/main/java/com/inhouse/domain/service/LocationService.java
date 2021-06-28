package com.inhouse.domain.service;

import java.util.List;

import com.inhouse.domain.object.Location;
import com.inhouse.domain.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 位置情報操作のロジック
 */
@Service
@RequiredArgsConstructor
public class LocationService {

    @Autowired
    private  LocationRepository repo;
    
    public List<Location> getList(){
        return repo.findAll();
    }

}
