package com.frame.template.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, String>, JpaSpecificationExecutor<E> {
}
