// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.storage.repository;

import com.frame.template.service.storage.domain.entity.File;


public interface FileRepository extends org.springframework.data.jpa.repository.JpaRepository<File, String>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<File> {


}

