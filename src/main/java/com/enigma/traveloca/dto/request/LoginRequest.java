package com.enigma.traveloca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "invalid username")
    @Size(min = 5, max = 20, message = "username must be greater than 5 and less than or equal 12 character")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
}
