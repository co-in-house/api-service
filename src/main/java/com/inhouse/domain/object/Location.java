package com.inhouse.domain.object;

import lombok.Builder;
import lombok.Data;

/**
 *  ロケーション
 */
@Data
@Builder
public class Location {
    
    /** ロケーションID */
    private int locationId;

    /** ロケーション名 */
    private String locationName;

}
