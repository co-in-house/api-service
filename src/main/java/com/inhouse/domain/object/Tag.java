package com.inhouse.domain.object;

import lombok.Builder;
import lombok.Data;

/**
 *  タグ
 */
@Data
@Builder
public class Tag {
    
    /** タグID */
    private int tagId;

    /** タグ名 */
    private String tagName;

    /** コミュニティID */
    private int communityId;

    /** タグ種別 */
    private int tagType;
}
