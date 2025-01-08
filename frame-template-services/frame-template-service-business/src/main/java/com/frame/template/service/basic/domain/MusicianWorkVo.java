package com.frame.template.service.basic.domain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicianWorkVo {

    private String workId;
    private String musicianId;

    @Schema(title = "状态")
    private String workStatus;

    @Schema(title = "作品名字")
    private String workName;

    @Schema(title = "播放次数")
    private Integer playTimes;

    @Schema(title = "tune_coins")
    private Integer tuneCoins;

    @Schema(title = "头像")
    private String avatar;

    @Column(name = "description")
    private String description;
}
