// ====================================================
//
// This file is part of the GstDev Verity Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2020 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.repository;

import com.gstdev.cloud.service.system.pojo.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;


public interface EmailRepository extends JpaRepository<Email, String>, JpaSpecificationExecutor<Email> {

    Email findByTokenAndType(String token, int type);

    Email findByTokenAndReceiverEmail(String token, String email);

    @Transactional
    @Modifying
    @Query(value = "update public.email set status = 1 where id = ?1", nativeQuery = true)
    void updateStatusById(String id);
}
