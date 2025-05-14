package com.example.developer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ClientData {
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
}
