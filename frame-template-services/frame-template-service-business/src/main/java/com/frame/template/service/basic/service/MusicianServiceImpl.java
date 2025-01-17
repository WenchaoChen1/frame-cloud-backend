package com.frame.template.service.basic.service;

import com.frame.template.service.basic.domain.entity.Musician;
import com.frame.template.service.basic.repository.MusicianRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
public class MusicianServiceImpl extends BaseServiceImpl<Musician, String, MusicianRepository> implements MusicianService {
    public MusicianServiceImpl(MusicianRepository baseRepository) {
        super(baseRepository);
    }
}
