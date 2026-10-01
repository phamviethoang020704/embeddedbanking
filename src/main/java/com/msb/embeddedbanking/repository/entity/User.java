package com.msb.embeddedbanking.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "role_id", nullable = false, insertable = false, updatable = false)
    private Long roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private Long createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_by")
    @LastModifiedBy
    private Long updatedBy;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    public boolean isDeleted() {
        return deletedAt != null;
    }
}
