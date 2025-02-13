package com.frame.template.service.basic.security.service;


import com.frame.template.service.basic.security.domain.User;
import com.gstdev.cloud.data.core.service.BaseService;

public interface UserService extends BaseService<User, String> {
    public User findByUser(User user);
}
