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
import com.frame.template.service.storage.domain.entity.File;
import com.frame.template.service.storage.mapper.FileMapper;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BasePOJOServiceImpl;
import com.gstdev.cloud.plugin.storage.core.service.StorageService;
//import com.gstdev.cloud.plugin.storage.core.storage.StorageProperties;
import com.frame.template.common.constant.FileConstants;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.service.storage.repository.FileRepository;
import io.undertow.security.idm.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FileServiceImpl extends BasePOJOServiceImpl<File, String, FileRepository, FileMapper, FileDto, FileInsertInput, FileUpdateInput, FilePageQueryCriteria, FileFindAllByQueryCriteria> implements FileService {

        @Resource
        private FileRepository fileRepository;
        @Resource
        private FileMapper fileMapper;
        @Resource
        private RedisCurrentLoginInformation redisCurrentLoginInformation;

//  @Resource
//  private StorageProperties storageProperties;

        @Resource
        private StorageService storageService;


        public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper) {
            super(fileRepository, fileMapper);
//        this.fileRepository = fileRepository;
//        this.fileMapper = fileMapper;
        }

        @Override
        public FileDto findByIdToDto(String id) {
            FileDto byId = super.findByIdToDto(id);
            String url = storageService.getObjectURL(byId.getBucketName(), byId.getName(), 2);
            byId.setLink(url);
            return byId;
        }

        @Override
        @Transactional
        public Result<FileDto> upload(MultipartFile file, String tenantId, FileConstants fileConstants) {
            File fileEntity = upload(file);
            fileEntity.setTenantId(tenantId);
            fileEntity.setServices(fileConstants.getService());
            fileEntity.setTableCode(fileConstants.getCode());
            FileDto fileDto = insertToDto(fileEntity);
            return Result.success(fileDto);
        }

        @Override
        @Transactional
        public Result<List<FileDto>> uploads(List<MultipartFile> file, String tenantId, FileConstants fileConstants) {
            if (file.size() == 0) {
                throw new PlatformRuntimeException("文件不能为空");
            }
            List<FileDto> fileDtos = new ArrayList<>();
            for (MultipartFile multipartFile : file) {
                File fileEntity = upload(multipartFile);
                fileEntity.setTenantId(tenantId);
                fileEntity.setServices(fileConstants.getService());
                fileEntity.setTableCode(fileConstants.getCode());
                FileDto fileDto = insertToDto(fileEntity);
                fileDtos.add(fileDto);
            }
            return Result.success(fileDtos);
        }

        public File upload(MultipartFile multipartFile) {
//    FileUtils.checkSize(1024, multipartFile.getSize());
//    String suffix = FileUtils.getExtensionName(multipartFile.getOriginalFilename());
//    InputStream stream = null;
//    String md5String = null;
//    try {
//      stream = multipartFile.getInputStream();
//      md5String = FileUtils.getMd5(multipartFile.getBytes());
//    } catch (Exception ex) {
//      throw new BadRequestException("文件上传失败");
//    }
//    //    List<FileObject> fileObjects = this.fileRepository.findByHash(md5String);
////    if (fileObjects.size() > 0) {
////      return fileObjects.get(0);
////    }
////    String bucketName = storageProperties.getBucketName();
//    String bucketName = "verity-development";
//    String randomObjectName = FileUtils.randomFileName(suffix);
//    String objectName = FileUtils.filePathByDate() + randomObjectName;
//    storageService.putObject(bucketName, objectName, stream, multipartFile.getSize(), multipartFile.getContentType());
//    String url = storageService.getObjectURL(bucketName, objectName, 2);
//
//    File fileEntity = new File();
//    fileEntity.setOriginalName(multipartFile.getOriginalFilename());
//    fileEntity.setContentType(multipartFile.getContentType());
//    fileEntity.setHash(md5String);
//    fileEntity.setBucketName(bucketName);
//    fileEntity.setLength(multipartFile.getSize());
//    fileEntity.setLink(url);
//    fileEntity.setName(objectName);
//    return fileEntity;
            return null;
        }



        /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/
    }

