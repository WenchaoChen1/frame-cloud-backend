package com.gstdev.template.service.system.feign.service;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.service.system.feign.FileFeignClient;
import com.gstdev.template.service.system.feign.vo.FileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author:
 * @date: 2023/1/3
 * @description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileFeignService {
  private final FileFeignClient fileFeignClient;

  public Result<FileVo> getById(String id) {
    return fileFeignClient.getById(id);
  }



}
