package com.gstdev.cloud.service.system.pojo.vo.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class MenuLinkQueryDto implements Serializable {
    String id;
    String name;
    String parentId;
    String url;
    String icon;
    String path;
    String permission;
    Integer type;
    Integer hidden;
    Integer status;
    Integer tenantEnable;
    Integer sort;
    String description;
    Integer deleted;
    Integer isLinkTenant;
    List<MenuLinkQueryDto> children;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    private String createdBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    private String updatedBy;
}
