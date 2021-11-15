package com.inhouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {
    
    @JsonProperty("url")
    private String url;

    @JsonProperty("createdAt")
    private String createdAt;

}
