package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.depart.*;
import com.frame.template.service.system.pojo.domain.Depart;
import com.gstdev.cloud.data.core.service.BaseTreeService;

public interface DepartService extends BaseTreeService<Depart,String,DepartDto, DepartInsertInput, DepartUpdateInput, DepartPageQueryCriteria, DepartFindAllByQueryCriteria> {
}
