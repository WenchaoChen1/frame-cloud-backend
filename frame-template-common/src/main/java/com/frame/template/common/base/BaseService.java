package com.frame.template.common.base;

import com.gstdev.cloud.commons.domain.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public interface BaseService<D extends BaseDto, II extends BaseInsertInput, UI extends BaseUpdateInput, PQC extends BasePageQueryCriteria
  , FQC extends BaseFindAllByQueryCriteria> {

  Result<D> insertToResult(II var1);

  Result<List<D>> insertAllToResult(List<II> var1);

  Result<D> updateToResult(UI varUpdateInput);

  Result<List<D>> updateAllToResult(List<UI> varUpdateInput);

  Result<D> deleteById(String id);

  Result<List<D>> deleteAllById(List<String> ids);

  Page<D> page(PQC queryCriteria, Pageable pageable);

  D findByIdToDto(String id);

  Result<D> findByIdToResult(String id);

  List<D> findAllByQueryCriteriaToDto(FQC var1);

  Result<List<D>> findAllByQueryCriteriaToResult(FQC var1);

}
