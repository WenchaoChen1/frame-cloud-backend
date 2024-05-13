// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.autoconfigure.service.identity.pojo.query;


import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class PostQueryCriteria implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query(type = Query.Type.IN)
    private Set<String> orgId;

    @Query(blurry = "name")
    private String name;
}
