
package com.inhouse.application.resource.location;

import java.util.List;

import com.inhouse.domain.object.Location;

import lombok.Data;

@Data
public class GetLocationListOutputDto {
    
    private List<Location> locationList;

}
