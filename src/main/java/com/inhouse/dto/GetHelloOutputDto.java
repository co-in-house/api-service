package com.inhouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetHelloOutputDto {
    
    @JsonProperty("hoge_message")
    private String hogeMessage;

    @JsonProperty("fuga_message")
    private String fugaMessage;

    
}
