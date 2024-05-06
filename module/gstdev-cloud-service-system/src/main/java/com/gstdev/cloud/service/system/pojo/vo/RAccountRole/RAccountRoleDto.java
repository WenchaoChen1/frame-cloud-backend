package com.gstdev.cloud.service.system.pojo.vo.RAccountRole;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.service.system.pojo.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 666
 * @date: 2022-12-06 11:31
 */
@Getter
@Setter
public class RAccountRoleDto implements Serializable {
    private String id;
    private SysAccount account;
    private Role role;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    private String createdBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    private String updatedBy;
}
