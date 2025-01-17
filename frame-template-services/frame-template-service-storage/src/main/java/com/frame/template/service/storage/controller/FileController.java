//====================================================
//
//This file is part of the GstDev Cloud Platform.
//
//Create by gstdev Tech <support@gstdev.com>
//Copyright (c) 2020-2025 gstdev.com
//
//====================================================

package com.frame.template.service.storage.controller;


import com.frame.template.service.storage.FileConstants;
import com.frame.template.service.storage.domain.base.*;
import com.frame.template.service.storage.domain.entity.File;
import com.frame.template.service.storage.mapper.FileMapper;
import com.frame.template.service.storage.mapper.vo.FileVoMapper;
import com.frame.template.service.storage.service.FileService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.rest.core.controller.BasePOJOController;
import com.gstdev.cloud.rest.core.controller.ResultController;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/file")
public class FileController implements ResultController {


    @Resource
    private FileService fileService;

    @Resource
    private FileVoMapper fileVoMapper;

    @Resource
    private FileMapper fileMapper;

    public FileService getService() {
        return fileService;
    }

    public FileVoMapper getVoMapper() {
        return fileVoMapper;
    }

    public FileMapper getMapper() {
        return fileMapper;
    }

    @GetMapping("/get-by-id")
    @Operation(summary = "根据id获取实体数据")
    public Result<FileVo> getById(String id) {
        return result(this.getMapper().toFileVo(getService().findById(id)));
    }

    @PostMapping
    @Operation(summary = "新增一条数据")
    public Result<FileVo> insert(FileInsertInput fileInsertInput, @RequestParam("file") MultipartFile file) {
        this.getService().insertAndUpdate(this.fileMapper.toEntity(fileInsertInput));
        return Result.success();
        //return insertToResult(fileInsertInput);
    }


    @Operation(summary = "Delete file")
    @DeleteMapping
    public Result<String> delete(@PathVariable final String id) {
        this.getService().deleteById(id);
        return Result.success();
        //return deleteByIdToResult(id);
    }

    @Operation(summary = "Upload file")
    @PostMapping(value = "/upload")
    public Result<FileVo> upload(@RequestParam("file") MultipartFile file, @RequestParam("tenantId") String tenantId, @RequestParam("type") FileConstants fileConstants) throws IOException {
        if (fileConstants == null) {

        }
        Result<FileVo> fileVoResult = getMapper().toVo(fileService.upload(file, tenantId, fileConstants));
        return fileVoResult;

    }

    @Operation(summary = "Uploads file")
    @PostMapping(value = "/uploads")
    public Result<List<FileVo>> uploads(@RequestParam("file") List<MultipartFile> file, @RequestParam("tenantId") String tenantId, @RequestParam("type") FileConstants fileConstants) throws IOException {
        Result<List<FileVo>> fileVoResult = getMapper().toAllVo(fileService.uploads(file, tenantId, fileConstants));
        return fileVoResult;
    }

}

