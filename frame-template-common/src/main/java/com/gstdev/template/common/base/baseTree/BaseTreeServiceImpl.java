package com.gstdev.template.common.base.baseTree;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.base.BaseRedisCurrentLoginInformation;
import com.gstdev.template.common.base.BaseServiceImpl;
import com.gstdev.template.common.utils.treeUtils.TreeFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <R>   xxxRepository
 * @param <M>   xxxMapper
 * @param <E>   xxx  实体类
 * @param <D>   xxxDto
 * @param <II>  xxxInsertInput
 * @param <UI>  xxxUpdateInput
 * @param <PQC> xxxPageQueryCriteria
 * @param <FQC> xxxFindAllByQueryCriteria
 */
public abstract class BaseTreeServiceImpl<R extends BaseTreeRepository<E>, M extends BaseTreeMapper<E, D, II, UI>,
  E extends BaseTreeEntity,
  D extends BaseTreeDto,
  II extends BaseTreeInsertInput,
  UI extends BaseTreeUpdateInput,
  PQC extends BaseTreePageQueryCriteria,
  FQC extends BaseTreeFindAllByQueryCriteria,
  RCLI extends BaseRedisCurrentLoginInformation>
  extends BaseServiceImpl<R, M, E, D, II, UI, PQC, FQC, RCLI> implements BaseTreeService<D, II, UI, PQC, FQC> {

  public BaseTreeServiceImpl(R repository, M mapper, RCLI redisCurrentLoginInformation) {
    super(repository, mapper, redisCurrentLoginInformation);
  }
  public List<E> findByParentId(String parentId) {
    List<E> byId = getRepository().findByParentId(parentId);
    return byId;
  }

  public List<D> findByParentIdToDto(String parentId) {
    List<D> byId = getMapper().toDto(findByParentId(parentId));
    return byId;
  }

  public List<E> findSubsets(String id) {
    List<E> subsets = findByParentId(id);
    List<E> data =new ArrayList<>();
    if(ObjectUtils.isEmpty(subsets)) {
      return subsets;
    }
    for (E subset : subsets) {
        data.addAll(findSubsets(subset.getId()));
    }
    return subsets;
  }

  public List<D> findSubsetsToDto(String id) {
    return getMapper().toDto(findSubsets(id));
  }

  public List<D> findItselfAndSubsetsToDto(String id) {
    List<D> subsetsToDto = findSubsetsToDto(id);
    subsetsToDto.add(findByIdToDto(id));
    return subsetsToDto;
  }

  public D findByIdToTreeToDto(String id) {
    D byId = findByIdToDto(id);
    List<D> children = findChildren(byId);
    byId.setChildren(children);
    return byId;
  }

  @Override
  @Transactional()
  public Result<D> findByIdToTreeToResult(String id) {
    return Result.success(findByIdToTreeToDto(id));
  }

  public List<D> findByParentIdToTreeToDto(String parentId) {
    D byId = findByIdToTreeToDto(parentId);
    return byId.getChildren();
  }

  @Override
  @Transactional()
  public Result<List<D>> findByParentIdToTreeToResult(String parentId) {
    return Result.success(findByParentIdToTreeToDto(parentId));
  }

  @Override
  @Transactional()
  public Result<List<D>> findByParentIdToResult(String parentId) {
    return Result.success(findByParentIdToDto(parentId));
  }

  public List<D> findAllByQueryCriteriaToDtoToTree(FQC queryCriteria) {
    List<D> allByQueryCriteriaToDto = findAllByQueryCriteriaToDto(queryCriteria);
//    List<D> ds = new TreeFactory<String, D>().buildTree(allByQueryCriteriaToDto);
    List<D> ds = new TreeFactory().buildTree(allByQueryCriteriaToDto);
    return ds;
  }

  @Override
  @Transactional()
  public Result<List<D>> findAllByQueryCriteriaToResultToTree(FQC queryCriteria) {
    return Result.success(findAllByQueryCriteriaToDtoToTree(queryCriteria));
  }


  public List<D> findChildren(D var) {
    List<D> byParentIdToDto = findByParentIdToDto(var.getId());
    if (!CollectionUtils.isEmpty(byParentIdToDto)) {
      byParentIdToDto.forEach(item -> {
        item.setChildren(findChildren(item));
      });
      return byParentIdToDto;
    }
    return null;
  }


}
