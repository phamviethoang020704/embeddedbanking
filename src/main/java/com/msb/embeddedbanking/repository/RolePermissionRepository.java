package com.msb.embeddedbanking.repository;

import com.msb.embeddedbanking.repository.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,RolePermission.RolePermissionId> {
    @Query("SELECT rp FROM RolePermission rp WHERE rp.roleId = :roleId")
    List<RolePermission> findByRoleId(@Param("roleId") Long roleId);
    @Query("SELECT rp.permissionId FROM RolePermission rp where rp.roleId = :roleId")
    List<Long> findPermissionIdByRoleId(@Param("roleId") Long roleId);
}
