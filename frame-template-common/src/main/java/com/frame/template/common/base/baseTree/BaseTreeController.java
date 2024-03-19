package com.frame.template.common.base.baseTree;

import com.frame.template.common.base.BaseController;
import com.gstdev.cloud.commons.web.Result;
import com.frame.template.common.base.BaseVoMapper;

import java.util.List;

/**
 * @param <S>   xxxService
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public abstract class BaseTreeController<S extends BaseTreeService<D, II, UI, PQC, FQC>,
  M extends BaseTreeVoMapper<V, D> & BaseVoMapper<V, D>, V extends BaseTreeVo, D extends BaseTreeDto, II extends BaseTreeInsertInput,
  UI extends BaseTreeUpdateInput, PQC extends BaseTreePageQueryCriteria
  , FQC extends BaseTreeFindAllByQueryCriteria> extends BaseController<S, M, V, D, II, UI, PQC, FQC> {

  public BaseTreeController(S service, M mapper) {
    super(service, mapper);
  }


  public Result<V> findByIdToTreeToResult(String id) {
    return getMapper().toVo(getService().findByIdToTreeToResult(id));
  }

  public Result<List<V>> findByParentIdIdToTreeToResult(String parentId) {
    return getMapper().toAllVo(getService().findByParentIdToTreeToResult(parentId));
  }

  public Result<List<V>> findByParentIdIdToResult(String parentId) {
    return getMapper().toAllVo(getService().findByParentIdToResult(parentId));
  }

  public Result<List<V>> findAllByQueryCriteriaToResultToTree(FQC queryCriteria) {
    return getMapper().toAllVo(getService().findAllByQueryCriteriaToResultToTree(queryCriteria));
  }



}
