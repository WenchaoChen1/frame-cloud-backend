package com.frame.template.service.basic.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gstdev.cloud.data.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;

@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "musician", schema = "public")
public class Musician  extends BaseEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "musician_id", length = 64, nullable = false)
    private String musicianId;

    @Column(name = "first_name", length = 60)
    private String firstName;

    @Column(name = "last_name", length = 60)
    private String lastName;

    @Schema(title = "性别")
    @Column(name = "gender", length = 1)
    private Integer gender = 0;

    @Schema(title = "最新草案")
    @Column(name = "latest_draft", length = 256)
    private String latestDraft;

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

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "musician", fetch = FetchType.LAZY)
    private List<MusicianWork> works;

    @JsonIgnore
    @OneToMany(mappedBy = "musician", fetch = FetchType.LAZY)
    private List<MusicianAward> awards;
}
