package com.frame.template.service.basic.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "musician_work", schema = "public")
public class MusicianWork {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "work_id", length = 64, nullable = false)
    private String workId;

    @Column(name = "musician_id", length = 64, nullable = false)
    private String musicianId;

    @Schema(title = "状态")
    @Column(name = "work_status", length = 50)
    private String workStatus;

    @Schema(title = "作品名字")
    @Column(name = "work_name", length = 100)
    private String workName;

    @Schema(title = "播放次数")
    @Column(name = "play_times", length = 11)
    private Integer playTimes;

    @Schema(title = "tune_coins")
    @Column(name = "tune_coins", length = 11)
    private Integer tuneCoins;

    @Schema(title = "头像")
    @Column(name = "avatar", length = 36)
    private String avatar;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musician_id",insertable=false, updatable=false)
    private Musician musician;

}
