package com.msb.embeddedbanking.repository.entity;

import com.msb.embeddedbanking.enums.role.RoleStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "roles")
@ToString(exclude = "permissions")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description",columnDefinition = "VARCHAR(1000)")
    private String description;

    @Column(name = "status",nullable = false,columnDefinition = "role_status")
    @Enumerated(EnumType.STRING)
    private RoleStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions = new ArrayList<>();
}
