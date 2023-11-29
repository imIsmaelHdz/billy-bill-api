package com.billy.operations.api.dto;

import lombok.*;

@Data
public class AuthenticationDto {
    private String email;
    private char[] password;
}
