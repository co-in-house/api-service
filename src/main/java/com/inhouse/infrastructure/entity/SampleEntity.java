package com.inhouse.infrastructure.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inhouse.domain.object.Sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RDBレコードのマッピング用クラス
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sample")
public class SampleEntity implements Serializable {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private int count;

    @Builder.Default
    @Column(name = "delete_flag")
    private int deleteFlag = 0;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "modified")
    private Timestamp modified;

    /**
     * ドメインオブジェクトからインスタンスを生成
     *
     * @param Sample ドメインオブジェクト
     * @return {@link SampleEntity}
     */
    public static SampleEntity build(Sample sample) {
        return SampleEntity.builder()
                    .name(sample.getName())
                    .count(sample.getCount())
                    .created(sample.getCreated())
                    .modified(sample.getModified())
                    .build();
    }

    /**
     * ドメインオブジェクトへ変換
     *
     * @return ドメインオブジェクト
     */
    public Sample toDomain() {
        return Sample.builder()
                .name(this.getName())
                .count(this.getCount())
                .created(this.getCreated())
                .modified(this.getModified())
                .build();

    }
}
