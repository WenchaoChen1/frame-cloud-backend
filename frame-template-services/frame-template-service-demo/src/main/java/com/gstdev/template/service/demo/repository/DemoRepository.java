package com.gstdev.template.service.demo.repository;

import com.gstdev.template.common.base.BaseRepository;
import com.gstdev.template.service.demo.pojo.domain.Demo;

import java.util.List;

public interface DemoRepository extends BaseRepository<Demo> {

  List<Demo> findAllByName(String name);

  Demo findByName(String name);

  Demo findByNameAndCode(String name, String code);
}
