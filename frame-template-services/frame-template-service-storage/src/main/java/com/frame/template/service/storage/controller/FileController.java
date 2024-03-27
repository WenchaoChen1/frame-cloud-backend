// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.storage.controller;

import com.frame.template.service.storage.domain.base.*;
import com.frame.template.service.storage.mapper.vo.FileVoMapper;
import com.frame.template.service.storage.service.FileService;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.BaseController;
import com.frame.template.common.constant.FileConstants;
import com.frame.template.service.storage.domain.base.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/v1/file")
public class FileController extends BaseController<FileService, FileVoMapper, FileVo, FileDto, FileInsertInput, FileUpdateInput, FilePageQueryCriteria, FileFindAllByQueryCriteria> {

  @Resource
  private FileService fileService;

  @Resource
  private FileVoMapper fileVoMapper;

  public FileController(FileService fileService, FileVoMapper fileVoMapper) {
    super(fileService, fileVoMapper);
    this.fileService = fileService;
    this.fileVoMapper = fileVoMapper;
  }

  @GetMapping("/get-by-id")
  @ApiOperation("根据id获取实体数据")
  public Result<FileVo> getById(String id) {
    return findByIdToResult(id);
  }

  @PostMapping
  @ApiOperation("新增一条数据")
  public Result<FileVo> insert(FileInsertInput fileInsertInput,@RequestParam("file") MultipartFile file) {
    return insertToResult(fileInsertInput);
}


  @ApiOperation("Delete file")
  @DeleteMapping
  public Result<FileVo> delete(@PathVariable final String id) {
    return deleteByIdToResult(id);
  }

  @ApiOperation("Upload file")
  @PostMapping(value = "/upload")
  public Result<FileVo> upload(@RequestParam("file") MultipartFile file, @RequestParam("tenantId") String tenantId,@RequestParam("type") FileConstants fileConstants) throws IOException {
    if(fileConstants==null){

    }
    Result<FileVo> fileVoResult = getMapper().toVo(fileService.upload(file,tenantId,fileConstants));
    return fileVoResult;

  }
  @ApiOperation("Uploads file")
  @PostMapping(value = "/uploads")
  public Result<List<FileVo>> uploads(@RequestParam("file") List<MultipartFile> file, @RequestParam("tenantId") String tenantId,@RequestParam("type") FileConstants fileConstants) throws IOException {
    Result<List<FileVo>> fileVoResult = getMapper().toAllVo(fileService.uploads(file,tenantId,fileConstants));
    return fileVoResult;
  }

}

