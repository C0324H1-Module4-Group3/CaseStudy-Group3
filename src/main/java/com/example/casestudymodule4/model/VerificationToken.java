package com.example.casestudymodule4.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token", columnDefinition = "VARCHAR(255)")
    private String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    private LocalDateTime expiryTime;

    public VerificationToken() {
    }

    public VerificationToken(User user, LocalDateTime expiryTime) {
        this.user = user;
        this.expiryTime = expiryTime;
    }

    public VerificationToken(String token, User user, LocalDateTime expiryTime) {
        this.token = token;
        this.user = user;
        this.expiryTime = expiryTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryDate) {
        this.expiryTime = expiryDate;
    }
}
