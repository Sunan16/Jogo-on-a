package com.jogonca.api_backend.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jogonca.api_backend.interfaces.IEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private Long coins;

    @Column(nullable = false)
    private String passwordHash;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
    private ZonedDateTime creatAt;

    private String recoveryToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
    private ZonedDateTime tokenExpiration;

    // Relações SQL

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Hability> habilities = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
        name = "item_user",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> itens = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Payment> payments = new HashSet<>();

    // Fim Relações SQL]

    public User() {
    }

    public User(Long id, String username, String email, Long coins, String passwordHash, ZonedDateTime creatAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.coins = coins;
        this.passwordHash = passwordHash;
        this.creatAt = creatAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    // Metodos Relações SQL

    public Set<Hability> getHabilities() {
        return habilities;
    }

    public Set<Item> getItens() {
        return itens;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    // Fim Metodos Relações SQL

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((coins == null) ? 0 : coins.hashCode());
        result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
        result = prime * result + ((creatAt == null) ? 0 : creatAt.hashCode());
        result = prime * result + ((recoveryToken == null) ? 0 : recoveryToken.hashCode());
        result = prime * result + ((tokenExpiration == null) ? 0 : tokenExpiration.hashCode());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (coins == null) {
            if (other.coins != null)
                return false;
        } else if (!coins.equals(other.coins))
            return false;
        if (passwordHash == null) {
            if (other.passwordHash != null)
                return false;
        } else if (!passwordHash.equals(other.passwordHash))
            return false;
        if (creatAt == null) {
            if (other.creatAt != null)
                return false;
        } else if (!creatAt.equals(other.creatAt))
            return false;
        if (recoveryToken == null) {
            if (other.recoveryToken != null)
                return false;
        } else if (!recoveryToken.equals(other.recoveryToken))
            return false;
        if (tokenExpiration == null) {
            if (other.tokenExpiration != null)
                return false;
        } else if (!tokenExpiration.equals(other.tokenExpiration))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", coins=" + coins + ", passwordHash="
                + passwordHash + ", creatAt=" + creatAt + ", recoveryToken=" + recoveryToken + ", tokenExpiration="
                + tokenExpiration + "]";
    }

    @PrePersist
    protected void onCreate() {
        creatAt = ZonedDateTime.now(ZoneId.of("GMT")); // Define a data/hora atual

    }
}
