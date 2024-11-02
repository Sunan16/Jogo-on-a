package com.jogonca.api_backend.models.dtos.receiveDTOs;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jogonca.api_backend.interfaces.AbstractDTOHateoas;

@JsonPropertyOrder({"id","username","email","coins","passwordHash","creatAt","recoveryToken","tokenExpiration", "itens"})
public class UserDTO extends AbstractDTOHateoas<UserDTO> {
    
    @JsonProperty("id")
    private Long key;
    private String username;
    private String email;
    private Long coins;
    private String passwordHash;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime creatAt;
    private String recoveryToken;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime tokenExpiration;
    private List<ItemDTO> itens;

    public UserDTO() { }

    public UserDTO(String username, String email, String passwordString){
        this.username = username;
        this.email = email;
        this.passwordHash = passwordString;
    }

    public UserDTO(Long key, String username, String email, Long coins, String passwordHash, ZonedDateTime creatAt,
            String recoveryToken, ZonedDateTime tokenExpiration) {
        this.key = key;
        this.username = username;
        this.email = email;
        this.coins = coins;
        this.passwordHash = passwordHash;
        this.creatAt = creatAt;
        this.recoveryToken = recoveryToken;
        this.tokenExpiration = tokenExpiration;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

    public ZonedDateTime getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(ZonedDateTime creatAt) {
        this.creatAt = creatAt;
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

    public List<ItemDTO> getItens() {
        return itens;
    }

}
