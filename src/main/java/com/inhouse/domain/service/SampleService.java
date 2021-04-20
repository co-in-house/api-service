package com.inhouse.domain.service;

import java.sql.Timestamp;
import java.util.Optional;

import com.inhouse.domain.object.Sample;
import com.inhouse.domain.repository.SampleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * ユーザ操作のロジック
 */
@Service
@RequiredArgsConstructor
public class SampleService {

    @Autowired
    private  SampleRepository repo;


    private final Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());


    /**
     * 作成・更新
     * @param inputDto
     * @return {@link Sample}型
     */
    public Sample hello(Sample inputDto){
        Optional<Sample> sampleOpt = repo.findByName(inputDto.getName());
        if(sampleOpt.isPresent()){
            inputDto.setCount(sampleOpt.get().getCount() + 1 );
            inputDto.setModified(currentTimestamp);
        }else{
            inputDto.setCount(1);
            inputDto.setCreated(currentTimestamp);
            inputDto.setModified(currentTimestamp);       
        }
        Sample sample = repo.save(inputDto);
        return sample;
    }

}
