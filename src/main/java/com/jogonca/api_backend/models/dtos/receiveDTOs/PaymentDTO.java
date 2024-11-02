package com.jogonca.api_backend.models.dtos.receiveDTOs;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jogonca.api_backend.interfaces.AbstractDTOHateoas;
import com.jogonca.api_backend.models.enums.Status;

@JsonPropertyOrder({"id","idUser","idStatus","value","creatAt"})
public class PaymentDTO extends AbstractDTOHateoas<PaymentDTO> {
    
    @JsonProperty("id")
    private Long key;
    private Long idUser;
    private Status status;
    private Double value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Instant creatAt;

    public PaymentDTO() { }

    public PaymentDTO(Long key, Long idUser, Status status, Double value, Instant creatAt) {
        this.key = key;
        this.idUser = idUser;
        this.status = status;
        this.value = value;
        this.creatAt = creatAt;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

}
