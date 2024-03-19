package com.gstdev.template.service.demo.service;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.base.BaseService;

import java.util.List;


public interface DemoService extends BaseService<com.gstdev.template.service.demo.pojo.base.demo.DemoDto, com.gstdev.template.service.demo.pojo.base.demo.DemoInsertInput, com.gstdev.template.service.demo.pojo.base.demo.DemoUpdateInput, com.gstdev.template.service.demo.pojo.base.demo.DemoPageQueryCriteria, com.gstdev.template.service.demo.pojo.base.demo.DemoFindAllByQueryCriteria> {

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

  com.gstdev.template.service.demo.pojo.base.demo.DemoDto findByName(String name);

  com.gstdev.template.service.demo.pojo.base.demo.DemoDto findByNameAndCode(String name, String code);


  List<com.gstdev.template.service.demo.pojo.base.demo.DemoDto> findAllByName(String name);

  Result<Object> updatexxxxxx(com.gstdev.template.service.demo.pojo.dto.demo.DemoUpdateXXXXXXInput demoUpdateXXXXXXInput);

  com.gstdev.template.service.demo.pojo.base.demo.DemoDto getCurrentLoginInformation(String name);
}
