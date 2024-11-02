package com.jogonca.api_backend.models.dtos.receiveDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jogonca.api_backend.interfaces.AbstractDTOHateoas;

@JsonPropertyOrder({"id","idPayment","coins"})
public class CoinDTO extends AbstractDTOHateoas<CoinDTO> {
    
    @JsonProperty("id")
    private Long key;
    private Long idPayment;
    private Long coins;

    public CoinDTO(){ }

    public Long getKey() {
        return key;
    }
    public void setKey(Long key) {
        this.key = key;
    }
    public Long getIdPayment() {
        return idPayment;
    }
    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }
    public Long getCoins() {
        return coins;
    }
    public void setCoins(Long coins) {
        this.coins = coins;
    }
    
}
