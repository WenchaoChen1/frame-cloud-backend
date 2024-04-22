package com.frame.template.service.demo.service;

import com.frame.template.service.demo.pojo.base.demo.*;
import com.frame.template.service.demo.pojo.dto.demo.*;
import com.gstdev.cloud.base.definition.domain.Result;

import java.util.List;


public interface DemoService extends BasePOJOService<DemoDto, DemoInsertInput, DemoUpdateInput, DemoPageQueryCriteria, DemoFindAllByQueryCriteria> {

//  Result<Object> insert(DemoInsertInput accountInsertInput);
//
//  Result<Object> update(DemoUpdateInput accountUpdateInput);
//
//  Result<Object> deleteById(String id);
//
//  Page<DemoDto> page(DemoPageQueryCriteria queryCriteria, Pageable pageable);
//
//  DemoDto findById(String id);
//
//  List<DemoDto> findAllByQueryCriteria(DemoFindAllByQueryCriteria queryCriteria);

//////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

  DemoDto findByName(String name);

  DemoDto findByNameAndCode(String name, String code);


  List<DemoDto> findAllByName(String name);

  Result<Object> updatexxxxxx(DemoUpdateXXXXXXInput demoUpdateXXXXXXInput);

  DemoDto getCurrentLoginInformation(String name);
}
