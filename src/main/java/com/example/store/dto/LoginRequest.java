package com.example.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
// DTO pour la requête
public   class LoginRequest {
    private String login;
    private String password;
    // getters & setters
}