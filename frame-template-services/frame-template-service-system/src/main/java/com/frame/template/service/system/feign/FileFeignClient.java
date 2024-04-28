package com.frame.template.service.system.feign;


import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.constant.ServiceConstants;
import com.frame.template.service.system.feign.vo.FileVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "fileClient", name = ServiceConstants.SERVICE_NAME_FILE, path = "/v1/file")
public interface FileFeignClient {


    @GetMapping("/get-by-id")
    Result<FileVo> getById(@RequestParam("id") String id);


}
