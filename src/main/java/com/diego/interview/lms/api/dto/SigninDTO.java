package com.diego.interview.lms.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SigninDTO {
    @JsonProperty
    @NotBlank(message = "Username is required")

    private String username;

    @JsonProperty
    @NotBlank(message = "Password is required")
    private String password;
}
