package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.dict.*;
import com.frame.template.service.system.pojo.domain.Dict;
import com.gstdev.cloud.data.core.service.BaseTreeService;

/**
 * @author zhucy
 */
public interface DictService extends BaseTreeService<Dict,String,DictDto, DictInsertInput, DictUpdateInput, DictPageQueryCriteria, DictFindAllByQueryCriteria> {
}
