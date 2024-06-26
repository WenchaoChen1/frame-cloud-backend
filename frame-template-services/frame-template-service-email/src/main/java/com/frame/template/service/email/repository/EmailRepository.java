// ====================================================
//
// This file is part of the GstDev Verity Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2020 gstdev.com
//
// ====================================================

package com.frame.template.service.email.repository;

import com.frame.template.service.email.pojo.entity.Email;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface EmailRepository extends JpaRepository<Email, String>, JpaSpecificationExecutor<Email> {

    Email findByTokenAndType(String token, int type);

    Email findByTokenAndReceiverEmail(String token, String email);

    @Transactional
    @Modifying
    @Query(value = "update public.email set status = 1 where id = ?1", nativeQuery = true)
    void updateStatusById(String id);
}
