package com.inhouse.domain.object;

import lombok.Builder;
import lombok.Data;

/**
 *  主要メンバー
 */
@Data
@Builder
public class PrimaryUser {
    
    /** 主要ユーザID */
    private int primaryUserId;

    /** 役割名 */
    private String roleName;

    /** ユーザ名 */
    private String userName;
}
