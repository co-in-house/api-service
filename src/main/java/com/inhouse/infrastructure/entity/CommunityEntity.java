package com.inhouse.infrastructure.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.inhouse.domain.object.Community;
import com.inhouse.domain.object.Location;
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
@Table(name = "community_info")
public class CommunityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "community_id_seq")
    @SequenceGenerator(name = "community_id_seq", sequenceName = "community_id_seq", allocationSize = 1)
    @Column(name = "community_id")
    private int communityId;

    @Column(name = "community_name")
    private String communityName;
    
    @Column(name = "content")
    private String content;

    @Column(name = "requirement")
    private String requirement;

    @OneToMany(mappedBy = "communityEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TagEntity> tags;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id")
    private LocationEntity localtion;

    @Column(name = "note")
    private String note;

    @Column(name = "profile_img_url")
    private String profileImgUrl;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "modified")
    private Timestamp modified;

    /**
     * ドメインオブジェクトからインスタンスを生成
     *
     * @param Community ドメインオブジェクト
     * @return {@link CommunityEntity}
     */
    public static CommunityEntity build(Community community) {

        return CommunityEntity.builder()
                    .communityId(community.getCommunityId())
                    .communityName(community.getCommunityName())
                    .content(community.getContent())
                    .requirement(community.getRequirement())
                    .localtion(
                            LocationEntity.build(
                                    Location.builder()
                                            .locationId(community.getLocationId())
                                            .locationName(community.getLocationName())
                                    .build()
                            )
                    )
                    .note(community.getNote())
                    .profileImgUrl(community.getIconImgUrl())
                    .created(community.getCreated())
                    .modified(community.getModified())
                    .build();
    }

    public Community toDomain() {
        List<Tag> tagList = new ArrayList<Tag>();
        if(!Objects.isNull(this.tags) && this.tags.size() > 0){
                for (TagEntity tagEntity : this.tags) {
                tagList.add(tagEntity.toDomain());
                }
        }
        return Community.builder()
                .communityId(this.communityId)
                .communityName(this.communityName)
                .content(this.content)
                .requirement(this.requirement)
                .locationId(this.localtion.getLocationId())
                .locationName(this.localtion.getLocationName())
                .note(this.note)
                .iconImgUrl(this.profileImgUrl)
                .tagList(tagList)
                .created(this.getCreated())
                .modified(this.getModified())
                .build();
    }
}
