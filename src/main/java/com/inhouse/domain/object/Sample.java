package com.inhouse.domain.object;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

/**
 * ユーザ
 */
@Data
@Builder
public class Sample {
    
    /* 処理回数 */
    private int count;

    /* 名前 */
    private String name;

    /* 作成日時 */
    private Timestamp created;

    /* 更新日時 */
    private Timestamp modified;
}

