package com.msb.embeddedbanking.repository;

import com.msb.embeddedbanking.repository.entity.Menu;
import com.msb.embeddedbanking.repository.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    @Query("SELECT p FROM Permission p WHERE p.id IN :ids")
    List<Permission> findAllByIds(@Param("ids") List<Long> ids);

    @Query("SELECT p.menu FROM Permission p WHERE p.id IN :ids")
    List<Menu> findMenusByPermissionIds(@Param("ids") List<Long> ids);
}
