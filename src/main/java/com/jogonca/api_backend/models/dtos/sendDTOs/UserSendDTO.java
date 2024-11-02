package com.jogonca.api_backend.models.dtos.sendDTOs;

import java.time.ZonedDateTime;

import com.jogonca.api_backend.interfaces.IDtoSend;

public class UserSendDTO implements IDtoSend<String> {

    private Long key;
    private String username;
    private String email;
    private Long coins;
    private String passwordHash;
    private String recoveryToken;
    private ZonedDateTime tokenExpiration;

    public UserSendDTO() {
    }

    public UserSendDTO(Long key, String username, String email, Long coins, String passwordHash, String recoveryToken,
            ZonedDateTime tokenExpiration) {
        this.key = key;
        this.username = username;
        this.email = email;
        this.coins = coins;
        this.passwordHash = passwordHash;
        this.recoveryToken = recoveryToken;
        this.tokenExpiration = tokenExpiration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRecoveryToken() {
        return recoveryToken;
    }

    public void setRecoveryToken(String recoveryToken) {
        this.recoveryToken = recoveryToken;
    }

    public ZonedDateTime getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(ZonedDateTime tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    @Override
    public String getIdentifier() {
        return getEmail();
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

}
