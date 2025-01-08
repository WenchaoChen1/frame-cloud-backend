package com.frame.template.service.basic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.pojo.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
public class MusicianDto extends BaseDto {
    private String musicianId;
    private String firstName;
    private String lastName;
    @Schema(title = "性别")
    private Integer gender = 0;
    @Schema(title = "最新草案")
    private String latestDraft;
    @Schema(title = "播放次数")
    private Integer playTimes;
    @Schema(title = "tune_coins")
    private Integer tuneCoins;
    @Schema(title = "头像")
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
}
