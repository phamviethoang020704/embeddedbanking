package com.msb.embeddedbanking.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "roles_permissions")
@EqualsAndHashCode(of = {"roleId", "permissionId"})
@EntityListeners(AuditingEntityListener.class)
@IdClass(RolePermission.RolePermissionId.class)

public class RolePermission {

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Id
    @Column(name = "permission_id")
    private Long permissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", insertable = false, updatable = false)
    private Permission permission;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RolePermissionId implements Serializable {
        private Long roleId;
        private Long permissionId;
    }
}
