package com.inhouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @JsonProperty("eventId")
    private Long eventId;

    @JsonProperty("communityId")
    private Long communityId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("start")
    private String start;

    @JsonProperty("end")
    private String end;

    @JsonProperty("location")
    private String locaiton;

    @JsonProperty("description")
    private String description;

    @JsonProperty("thumbnailImg")
    private String thumbnailImg;


}
