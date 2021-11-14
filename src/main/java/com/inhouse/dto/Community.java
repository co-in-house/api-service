package com.inhouse.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Community {
    
    @JsonProperty("communityId")
    private Long communityId;

    @JsonProperty("communityName")
    private String communityName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("description")
    private String description;

    @JsonProperty("icon_img")
    private String iconImg;

}
