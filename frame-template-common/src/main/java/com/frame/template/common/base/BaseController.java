package com.frame.template.common.base;

import com.gstdev.cloud.commons.ass.definition.domain.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @param <S>   xxxService
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public abstract class BaseController<S extends BaseService<D, II, UI, PQC, FQC>, M extends BaseVoMapper<V, D>, V extends BaseVo, D extends BaseDto, II extends BaseInsertInput, UI extends BaseUpdateInput, PQC extends BasePageQueryCriteria
  , FQC extends BaseFindAllByQueryCriteria> {

  protected S service;

  private M mapper;

  public BaseController(S service, M mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  public M getMapper() {
    return mapper;
  }

  public void setMapper(M mapper) {
    this.mapper = mapper;
  }

  public S getService() {
    return this.service;
  }

  public void setService(S service) {
    this.service = service;
  }


  protected Result<V> insertToResult(II var1) {
//    try {
      return getMapper().toVo(getService().insertToResult(var1));
//    } catch (Exception e) {
//      e.printStackTrace();
//      return Result.failure("Save failed");
//    }
  }

  protected Result<List<V>> insertAllToResult(List<II> var1) {
      return getMapper().toAllVo(getService().insertAllToResult(var1));
  }

  protected Result<V> updateToResult(UI var1) {
      return getMapper().toVo(getService().updateToResult(var1));
  }

  protected Result<List<V>> updateAllToResult(List<UI> var1) {
      return getMapper().toAllVo(getService().updateAllToResult(var1));
  }


  protected Result<V> deleteByIdToResult(String id) {
      return getMapper().toVo(getService().deleteById(id));
  }

  protected Result<List<V>> deleteAllByIdToResult(List<String> ids) {
      return getMapper().toAllVo(getService().deleteAllById(ids));
  }

  protected Result<Page<V>> pageToResult(PQC queryCriteria, Pageable pageable) {
      return Result.success(getMapper().toVo(getService().page(queryCriteria, pageable)));
  }

  protected Result<V> findByIdToResult(String id) {
      return getMapper().toVo(getService().findByIdToResult(id));
  }

  protected Result<List<V>> findAllByQueryCriteriaToResult(FQC var1) {
      return getMapper().toAllVo(getService().findAllByQueryCriteriaToResult(var1));
  }


}
