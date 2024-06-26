// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.autoconfigure.service.identity.service;

import com.frame.template.autoconfigure.service.identity.mapper.UserMapper;
import com.frame.template.autoconfigure.service.identity.pojo.UserInput;
import com.frame.template.autoconfigure.service.identity.pojo.dto.UserDto;
import com.frame.template.autoconfigure.service.identity.pojo.entity.User;
import com.frame.template.autoconfigure.service.identity.pojo.query.PostQueryCriteria;
import com.frame.template.autoconfigure.service.identity.repository.UserRepository;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.utils.PageUtils;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<Object> getUserData(PostQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, criteria, criteriaBuilder), pageable);
        return Result.success(PageUtils.toPage(page.map(userMapper::toDto)));
    }

    @Override
    @Transactional
    public Result<UserDto> create(UserInput userInput) {
        User post = userMapper.toEntity(userInput);
        post.setUserId(userInput.getUserId());
        post.setEmail(userInput.getEmail());
        post.setUsername(userInput.getUsername());
        post.setPassword(userInput.getPassword());
        User result = userRepository.save(post);
        return Result.success(userMapper.toDto(result));
    }

    @Override
    public Result<Object> update(UserInput userInput) {
        getUserById(userInput.getId());
        User entity = userMapper.toEntity(userInput);

        User result = userRepository.save(entity);
        return Result.success(userMapper.toDto(result));
    }

    @Override
    public Result<Object> delete(String postId) {
        User user = getUserById(postId);
        userRepository.save(user);
        return Result.success();
    }

    @Override
    public User getUserByUserId(String userId) {
        return getUserById(userId);
    }

    @Override
    public String findByUserId(String userId) {
        return userRepository.findById(userId).get().getUserId();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private User getUserById(String postId) {
        User user = userRepository.findById(postId).orElseGet(User::new);
        if (Objects.isNull(user)) {
            throw new PlatformRuntimeException("该用户不存在！");
        }
        return user;
    }
}

