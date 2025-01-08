package com.frame.template.service.basic.mapper;

import com.frame.template.service.basic.domain.*;
import com.frame.template.service.basic.domain.entity.Musician;
import com.frame.template.service.basic.domain.entity.MusicianAward;
import com.frame.template.service.basic.domain.entity.MusicianWork;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface MusicianMapper  extends BasePOJOMapper<Musician, MusicianDto, MusicianVo, MusicianInsertInput, MusicianUpdateInput> {

    List<MusicianManagePageVo> toMusicianManagePageVo(List<Musician> musicians);
    default Page<MusicianManagePageVo> toMusicianManagePageVo(Page<Musician> page) {
        /*List<Musician> musicians = page.getContent();
        List<MusicianManagePageVo> responses =  musicians.stream().map(musician->{
                MusicianManagePageVo musicianManagePageVo = toMusicianManageVo(musician);
                musicianManagePageVo.setWorks(toMusicianWorkVo(musician.getWorks()));
                musicianManagePageVo.setAwards(toMusicianAwardVo(musician.getAwards()));
                return musicianManagePageVo;
            }
        ).collect(Collectors.toList());*/
       List<MusicianManagePageVo> responses = this.toMusicianManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    default MusicianManageDetailVo toMusicianDetailVo(Musician musician){
        MusicianManageDetailVo musicianManageDetailVo = toMusicianManageDetailVo(musician);
        musicianManageDetailVo.setWorks(toMusicianWorkVo(musician.getWorks()));
        musicianManageDetailVo.setAwards(toMusicianAwardVo(musician.getAwards()));
        return musicianManageDetailVo;
    };

    MusicianManageDetailVo toMusicianManageDetailVo(Musician musician);

    MusicianManagePageVo toMusicianManageVo(Musician musician);

    List<MusicianWorkVo> toMusicianWorkVo(List<MusicianWork> works);

    List<MusicianAwardVo> toMusicianAwardVo(List<MusicianAward> awards);
}
