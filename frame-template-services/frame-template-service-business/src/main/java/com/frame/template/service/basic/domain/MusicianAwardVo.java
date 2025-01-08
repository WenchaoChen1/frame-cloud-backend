package com.frame.template.service.basic.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicianAwardVo {

    private String awardId;

    private String musicianId;

    @Schema(title = "名字")
    private String awardName;

    @Schema(title = "头像")
    private String avatar;

    @Column(name = "description")
    private String description;
}
