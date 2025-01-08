package com.frame.template.service.basic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MusicianManageDetailVo {
    private String musicianId;
    private String firstName;
    private String lastName;
    private Integer gender = 0;
    private String latestDraft;
    private Integer playTimes;
    private Integer tuneCoins;
    private String avatar;
    private String role;
    private String description;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
    private List<MusicianWorkVo> works;
    private List<MusicianAwardVo> awards;
}
