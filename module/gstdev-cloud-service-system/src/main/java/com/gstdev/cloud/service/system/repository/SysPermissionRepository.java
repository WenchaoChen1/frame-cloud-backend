
package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.data.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SysPermissionRepository extends BaseRepository<SysPermission, String> {

    @Modifying
    @Transactional
    @Query("UPDATE SysPermission p SET p.status = :status WHERE p.permissionType = :permissionType")
    void updateStatusByPermissionType(@Param("status") DataItemStatus status, @Param("permissionType") String permissionType);

}
