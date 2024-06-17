//package com.gstdev.cloud.service.system.pojo.vo.depart;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.gstdev.cloud.service.system.util.Treeify;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//
//@Getter
//@Setter
//public class DepartDto implements Serializable, Treeify<DepartDto, String> {
//    List<DepartDto> children;
//    private String id;
//    private String tenantId;
//    private String parentId;
//    private String name;
//    private String code;
//    private String shortName;
//    private Integer status;
//    private Integer sort;
//    private String description;
//    private Integer deleted;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date createdAt;
//    private String createdBy;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date updatedAt;
//    private String updatedBy;
//}
