
package com.inhouse.application.resource.community;

import javax.validation.constraints.NotNull;

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
    public String toDomain() {
        return keyword;
    }

    public void build(String keyword){
        this.setKeyword(keyword);
    }
}
