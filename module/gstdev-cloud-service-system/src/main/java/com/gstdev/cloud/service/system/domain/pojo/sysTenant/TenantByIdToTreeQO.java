package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TenantByIdToTreeQO implements Serializable {
    private static final long serialVersionUID = 3163118978801722144L;

    @Query(blurry = "tenantName", type = Query.Type.IN)
    private String tenantName;
    //  @Query
    private String tenantId;
    //  @Query(blurry = "tenantId", type = Query.Type.IN)
    @Query(type = Query.Type.IN)
//    @Query(blurry = "id",type = Query.Type.EQUAL)
    private List<String> id;


    public List<String> getTenantIds() {
        return id;
    }

    public void setTenantIds(List<String> tenantIds) {
        this.id = tenantIds;
    }
}
