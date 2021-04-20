package com.inhouse.application.resource.sample;

import javax.validation.constraints.NotNull;
import com.inhouse.domain.object.Sample;

import lombok.Data;

@Data
public class HelloWorldInputDto {
 
    @NotNull
    private String name;

    /**
     * ドメインオブジェクトへ変換
     *
     * @return ドメインオブジェクト
     */
    public Sample toDomain() {
        return Sample.builder()
                .name(this.name)
                .build();
    }

    public void build(String name){
        this.setName(name);
    }
}
