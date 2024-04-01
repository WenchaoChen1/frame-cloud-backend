package com.frame.template.common.base.baseTree;

import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.common.base.BaseService;

import java.util.List;

/**
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public interface BaseTreeService<D extends BaseTreeDto, II extends BaseTreeInsertInput,
  UI extends BaseTreeUpdateInput, PQC extends BaseTreePageQueryCriteria
  , FQC extends BaseTreeFindAllByQueryCriteria> extends BaseService<D, II, UI, PQC, FQC> {

  List<D> findItselfAndSubsetsToDto(String id);

  List<D> findSubsetsToDto(String id);

  D findByIdToTreeToDto(String id);

  Result<D> findByIdToTreeToResult(String id);

  List<D> findByParentIdToTreeToDto(String parentId);

  Result<List<D>> findByParentIdToTreeToResult(String parentId);

  List<D> findByParentIdToDto(String parentId);

  Result<List<D>> findByParentIdToResult(String parentId);

  List<D> findAllByQueryCriteriaToDtoToTree(FQC queryCriteria);

  Result<List<D>> findAllByQueryCriteriaToResultToTree(FQC queryCriteria);

}
