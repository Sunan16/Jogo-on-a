package com.jogonca.api_backend.models.dtos.sendDTOs;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jogonca.api_backend.interfaces.IDtoSend;
import com.jogonca.api_backend.models.enums.Status;

public class PayRequestDTO implements IDtoSend<Long> {

    private Long key;
    private String email;
    private Integer status;
    private Long coinsValue;
    private Double value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Instant creatAt;

    public PayRequestDTO() {
    }

    public Long getCoinsValue() {
        return coinsValue;
    }

    public void setCoinsValue(Long coinsValue) {
        this.coinsValue = coinsValue;
    }

    public String getEmail() {
        return email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status valueOfStatus() {
        return Status.valueOf(this.getStatus());
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Instant getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Instant creatAt) {
        this.creatAt = creatAt;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    @Override
    public Long getIdentifier() {
        return getKey();
    }
}