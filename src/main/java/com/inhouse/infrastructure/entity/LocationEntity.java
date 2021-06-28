package com.inhouse.infrastructure.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inhouse.domain.object.Location;

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
@Table(name = "location_info")
public class LocationEntity implements Serializable {

    @Id
    @Column(name = "location_id")
    private int locationId;

    @Column(name = "location_name")
    private String locationName;

    public static LocationEntity build(Location location) {
        return LocationEntity.builder()
                    .locationId(location.getLocationId())
                    .locationName(location.getLocationName())
                    .build();
    }


    public Location toDomain() {
        return Location.builder()
                .locationId(this.locationId)
                .locationName(this.locationName)
                .build();
    }
}
