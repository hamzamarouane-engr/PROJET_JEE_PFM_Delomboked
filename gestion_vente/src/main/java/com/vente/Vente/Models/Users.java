package com.vente.Vente.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Entity representing a user in the sales system.
 * Used for authentication and authorization with JWT.
 */
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "role", nullable = false, length = 20)
    @NotBlank(message = "Role is required")
    private String role; // ROLE_ADMIN, ROLE_USER

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "fullname", length = 100)
    private String fullName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    public Users() {
    }

    public Users(Long userId, String username, String password, String role, Boolean enabled, String fullName,
            String email, LocalDateTime createdAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (enabled == null) {
            enabled = true;
        }
        if (role == null) {
            role = "ROLE_USER";
        }
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
