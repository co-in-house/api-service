
package com.inhouse.application.resource.community;

import javax.validation.constraints.NotNull;

import com.inhouse.domain.object.Sample;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCommunitiesListInputDto {
 
    @NotNull
    private String keyword;

    /**
     * ドメインオブジェクトへ変換
     *
     * @return ドメインオブジェクト
     */
    public Sample toDomain() {
        return Sample.builder()
                .name(this.keyword)
                .build();
    }

    public void build(String keyword){
        this.setKeyword(keyword);
    }
}
