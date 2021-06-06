package com.inhouse.infrastructure.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.inhouse.domain.object.Tag;

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
@Table(name = "tag_info")
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_seq")
    @SequenceGenerator(name = "tag_id_seq", sequenceName = "tag_id_seq", allocationSize = 1)
    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "tag_type")
    private int tagType;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "community_id")
    private CommunityEntity communityEntity;


    public Tag toDomain() {
        return Tag.builder()
                .tagId(this.tagId)
                .tagName(this.tagName)
                .tagType(this.tagType)
                .communityId(this.communityEntity.getCommunityId())
                .build();
    }

    public static TagEntity build(Tag tag) {
        return TagEntity.builder()
                    .tagId(tag.getTagId())
                    .tagName(tag.getTagName())
                    .tagType(tag.getTagType())
                    .communityEntity(CommunityEntity.builder().communityId(tag.getCommunityId()).build())
                    .build();
    }
}
