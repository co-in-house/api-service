package com.inhouse.domain.object;

import java.sql.Timestamp;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * コミュニティ
 */
@Data
@Builder
public class Community {
    
    /** コミュニティID */
    private int communityId;

    /** コミュニティID */
    private String communityName;
    
    /** 活動内容 */
    private String content;

    /** 位置ID*/
    private int locationId;

    /** 位置ラベル*/
    private String locationName;

    /** 備考 */
    private String note;

    /** プロフィール画像　URL */
    private String iconImgUrl;

    /** タグリスト */
    private List<Tag> tagList;

    /** 入会条件 */
    private String requirement;

    /** 作成日時 */
    private Timestamp created;

    /** 更新日時 */
    private Timestamp modified;
}

