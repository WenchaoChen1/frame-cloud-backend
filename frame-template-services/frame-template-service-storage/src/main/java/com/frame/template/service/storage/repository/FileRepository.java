// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.storage.repository;

import com.frame.template.service.storage.domain.entity.File;


public interface FileRepository extends org.springframework.data.jpa.repository.JpaRepository<File, String>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<File> {


}

