package com.msb.embeddedbanking.repository.entity;

import com.msb.embeddedbanking.enums.Action;
import com.msb.embeddedbanking.enums.PermissionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions")
@EntityListeners(AuditingEntityListener.class)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_id", insertable = false, updatable = false)
    private Long menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "code",nullable = false,length = 100)
    private String code;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private Action action;

    @Column(name = "description",columnDefinition = "VARCHAR(1000)")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PermissionStatus status;

    @Column(name = "created_at",nullable = false,updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
