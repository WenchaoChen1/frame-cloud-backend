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
@Table(name = "musician_award", schema = "public")
public class MusicianAward {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "award_id", length = 64, nullable = false)
    private String awardId;

    @Column(name = "musician_id", length = 64, nullable = false)
    private String musicianId;

    @Column(name = "award_name", length = 100, nullable = false)
    private String awardName;

    @Schema(title = "头像")
    @Column(name = "avatar", length = 36)
    private String avatar;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musician_id",insertable=false, updatable=false)
    private Musician musician;
}
