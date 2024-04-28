// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.storage.service;


import com.frame.template.service.storage.domain.base.*;
import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.constant.FileConstants;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileService extends BasePOJOService<FileDto, FileInsertInput, FileUpdateInput, FilePageQueryCriteria, FileFindAllByQueryCriteria> {
  Result<FileDto> upload(MultipartFile file, String tenantId, FileConstants fileConstants);

  Result<List<FileDto>> uploads(List<MultipartFile> file, String tenantId, FileConstants fileConstants);

  /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/
}

