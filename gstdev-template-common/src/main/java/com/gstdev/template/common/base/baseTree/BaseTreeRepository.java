package com.gstdev.template.common.base.baseTree;

import com.gstdev.template.common.base.BaseRepository;

import java.util.List;

public interface BaseTreeRepository<E extends BaseTreeEntity> extends BaseRepository<E> {
  List<E> findByParentId(String parentId);
}
