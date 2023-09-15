package com.diego.interview.lms.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    @NotBlank(message = "First name is required")
    @JsonProperty
    private String firstName;

    @NotBlank(message = "Last name is required")
    @JsonProperty
    private String lastName;

    @NotNull(message = "DOB is required")
    @JsonProperty
    private LocalDate dob;

    @NotBlank(message = "Adress is required")
    @JsonProperty
    private String adress;

    @NotBlank(message = "Phone number is required")
    @JsonProperty
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @JsonProperty
    private String email;

    @NotBlank(message = "Username is required")
    @JsonProperty
    private String username;

    @NotBlank(message = "Password is required")
    @JsonProperty
    private String password;

}
