package com.jogonca.api_backend.models.dtos.sendDTOs;

import com.jogonca.api_backend.interfaces.IDtoSend;

public class CoinSendDTO implements IDtoSend<String> {

    private String email;
    private Long coins;

    public CoinSendDTO() { }

    public CoinSendDTO(String email, Long coins) {
        this.email = email;
        this.coins = coins;
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

    @Override
    public String getIdentifier() {
        return getEmail();
    }
    
}
