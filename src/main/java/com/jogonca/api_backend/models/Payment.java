package com.jogonca.api_backend.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jogonca.api_backend.interfaces.IEntity;
import com.jogonca.api_backend.models.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment implements IEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    private Status status;

    @Column(name = "payment_value")
    private Double value;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant creatAt;

    // Relações SQL

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(mappedBy = "payment", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Coin coin;

    // Fim Relações SQL

    public Payment() { }

    public Payment(Long id, Status status, Double value, Instant creatAt, User user, Coin coin) {
        this.id = id;
        this.status = status;
        this.value = value;
        this.creatAt = creatAt;
        this.user = user;
        this.coin = coin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    // Metodos Relações SQL

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    // Fim Metodos Relações SQL
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((creatAt == null) ? 0 : creatAt.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((coin == null) ? 0 : coin.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (status != other.status)
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (creatAt == null) {
            if (other.creatAt != null)
                return false;
        } else if (!creatAt.equals(other.creatAt))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (coin == null) {
            if (other.coin != null)
                return false;
        } else if (!coin.equals(other.coin))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Payment [id=" + id + ", status=" + status + ", value=" + value + ", creatAt=" + creatAt + ", user="
                + user + ", coin=" + coin + "]";
    }

}
