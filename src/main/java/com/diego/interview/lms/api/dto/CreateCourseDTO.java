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
public class CreateCourseDTO {

    @NotBlank(message = "Name is required")
    @JsonProperty
    private String name;

    @NotBlank(message = "Description is required")
    @JsonProperty
    private String description;

}
