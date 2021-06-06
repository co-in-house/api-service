
package com.inhouse.application.resource.community;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.inhouse.domain.object.Community;
import com.inhouse.domain.object.Tag;
import com.inhouse.util.CommonConst;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCommunityInputDto {
 
    @NotNull
    /** コミュニティ名 */
    private String communityName;
    
    /** 活動内容 */
    private String content;
    
    @NotNull
    /** 位置ID*/
    private int locationId;

    /** 備考 */
    private String note;

    /** プロフィール画像データ */

    /** ヘッダー画像データ */

    /** タグリスト */
    private List<String> tagList;

    /** 入会条件 */
    private String requirement;
  

    /**
     * ドメインオブジェクトへ変換
     *
     * @return ドメインオブジェクト
     */
    public Community toDomain() {
        List<Tag> tags = new ArrayList<Tag>();
        if(!Objects.isNull(this.tagList) && this.tagList.size() > 0){
            for (String tag : this.tagList) {
                tags.add(Tag.builder().tagName(tag).tagType(CommonConst.TAG_TYPE_NORMAL).build());
            }
        }
        return Community.builder()
                .communityName(this.communityName)
                .content(this.content)
                .locationId(this.locationId)
                .note(this.note)
                .tagList(tags)
                .requirement(this.requirement)
                .build();
    }

}
