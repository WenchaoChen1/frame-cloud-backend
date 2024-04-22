package com.frame.template.service.demo.repository;

import com.frame.template.service.demo.pojo.domain.Demo;

import java.util.List;

public interface DemoRepository extends org.springframework.data.jpa.repository.JpaRepository<Demo, String>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<Demo> {

  List<Demo> findAllByName(String name);

  Demo findByName(String name);

  Demo findByNameAndCode(String name, String code);
}
