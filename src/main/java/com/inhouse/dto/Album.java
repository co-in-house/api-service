package com.inhouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
    
    @JsonProperty("albumId")
    private Long albumId;

    @JsonProperty("albumName")
    private String albumName;

    @JsonProperty("thumbnailImg")
    private String thumbnailImg;

    @JsonProperty("createdAt")
    private String createdAt;

}
