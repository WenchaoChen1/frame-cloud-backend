package com.gstdev.template.common.base;

import com.gstdev.cloud.commons.exception.BadRequestException;
import com.gstdev.cloud.commons.web.Result;
import com.gstdev.cloud.data.jpa.utils.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public abstract class BaseServiceImpl<R extends BaseRepository<E>, M extends BaseMapper<E, D, II, UI>, E extends BaseEntity, D extends BaseDto, II extends BaseInsertInput, UI extends BaseUpdateInput, PQC extends BasePageQueryCriteria
  , FQC extends BaseFindAllByQueryCriteria, RCLI extends BaseRedisCurrentLoginInformation> implements BaseService<D, II, UI, PQC, FQC> {

  private R repository;

  private M mapper;

  private RCLI redisCurrentLoginInformation;

  public BaseServiceImpl(R repository, M mapper, RCLI redisCurrentLoginInformation) {
    this.repository = repository;
    this.mapper = mapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }

  public R getRepository() {
    return repository;
  }

  public void setRepository(R repository) {
    this.repository = repository;
  }

  public M getMapper() {
    return mapper;
  }

  public void setMapper(M mapper) {
    this.mapper = mapper;
  }

  public RCLI getRedisCurrentLoginInformation() {
    return redisCurrentLoginInformation;
  }

  public void setRedisCurrentLoginInformation(RCLI redisCurrentLoginInformation) {
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }

  @Transactional()
  public E save(E var) {
    var.setUpdatedBy(getRedisCurrentLoginInformation().getCurrentLoginAccountId());
    return getRepository().save(var);
  }

  @Transactional()
  public List<E> saveAll(List<E> var) {
    return getRepository().saveAll(var);
  }


  @Transactional()
  public E insert(E var) {
    var.setCreatedBy(getRedisCurrentLoginInformation().getCurrentLoginAccountId());
    return save(var);
  }

  @Transactional()
  public List<E> insertAll(List<E> e) {
    List<E> es = new ArrayList<>();
    for (E e1 : e) {
      es.add(insert(e1));
    }
    return es;
  }

  @Transactional()
  public D insertToDto(E e) {
    return getMapper().toDto(insert(e));
  }

  @Override
  @Transactional()
  public Result<D> insertToResult(II varInsertUpdate) {
    E e = toEntityInsert(varInsertUpdate);
    return Result.success(insertToDto(e));
  }

  @Transactional()
  public List<D> insertAllToDto(List<E> e) {
    return getMapper().toDto(insertAll(e));
  }

  @Override
  @Transactional()
  public Result<List<D>> insertAllToResult(List<II> varInsertInput) {
    List<E> e = toEntityInsert(varInsertInput);
    return Result.success(insertAllToDto(e));
  }

  @Transactional()
  public E update(E e) {
    return save(e);
  }

  @Transactional()
  public List<E> updateAll(List<E> e) {
    List<E> es = new ArrayList<>();
    for (E e1 : e) {
      es.add(update(e1));
    }
    return es;
  }


  @Transactional()
  public D updateToDto(E e) {
    return getMapper().toDto(save(e));
  }

  @Override
  @Transactional()
  public Result<D> updateToResult(UI varUpdateInput) {
    return Result.success(updateToDto(toEntityUpdate(varUpdateInput)));
  }

  @Transactional()
  public List<D> updateAllToDto(List<E> e) {
    return getMapper().toDto(updateAll(e));
  }

  @Override
  @Transactional()
  public Result<List<D>> updateAllToResult(List<UI> varUpdateInput) {
    List<E> e = toEntityUpdate(varUpdateInput);
    return Result.success(updateAllToDto(e));
  }

  @Override
  @Transactional()
  public Result<D> deleteById(String id) {
    if (id == null || id.length() == 0) {
      throw new BadRequestException("The primary key cannot be empty");
    }
    getRepository().deleteById(id);
    return Result.success(null);
  }

  @Override
  @Transactional()
  public Result<List<D>> deleteAllById(List<String> ids) {
    ids.forEach(id -> {
      deleteById(id);
    });
    return Result.success(null);
  }


  @Override
  public Page<D> page(PQC pageQueryCriteria, Pageable pageable) {
    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "updatedAt"));
    Page<E> page = getRepository().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, pageQueryCriteria, criteriaBuilder), pageable);
    return getMapper().toDto(page);
  }

  public E findById(String id) {
    Optional<E> byId = getRepository().findById(id);
    E e = null;
    if (byId.isPresent()) {
      e = byId.get();
    }
    return e;
  }

  @Override
  public D findByIdToDto(String id) {
    return getMapper().toDto(findById(id));
  }
  @Override
  public Result<D> findByIdToResult(String id) {
    return  Result.success(findByIdToDto(id));
  }

  @Override
  public List<D> findAllByQueryCriteriaToDto(FQC queryCriteria) {
    return getMapper().toDto(getRepository().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder)));
  }
  @Override
  public Result<List<D>> findAllByQueryCriteriaToResult(FQC queryCriteria) {
    return Result.success(findAllByQueryCriteriaToDto(queryCriteria));
  }


  public E toEntityInsert(II var1) {
    E e = getMapper().toEntityInsert(var1);
    return e;
  }

  public List<E> toEntityInsert(List<II> var1) {
    List<E> e = new ArrayList<>();
    for (II ii : var1) {
      e.add(toEntityInsert(ii));
    }
    return e;
  }

  public E toEntityUpdate(UI var1) {
    String id = null;
    try {
      Method method = var1.getClass().getMethod("getId", new Class[]{});
      Object value = method.invoke(var1, new Object[]{});
      id = String.valueOf(value);
    } catch (Exception e) {
      throw new BadRequestException("The primary key cannot be empty");
    }
    if (id.equals("null")) {
      throw new BadRequestException("The primary key cannot be empty");
    }
    Optional<E> byId = getRepository().findById(id);
    if (byId.isEmpty()) {
      throw new BadRequestException("The primary key cannot be empty");
    }
    E e = null;
    if (byId.isPresent()) {
      e = byId.get();
    }
    getMapper().copyUpdate(var1, byId.get());
    return e;
  }

  public List<E> toEntityUpdate(List<UI> var1) {
    List<E> e = new ArrayList<>();
    for (UI ui : var1) {
      e.add(toEntityUpdate(ui));
    }
    return e;
  }
}
