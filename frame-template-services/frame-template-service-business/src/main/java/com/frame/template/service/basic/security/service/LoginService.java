package com.frame.template.service.basic.security.service;

import com.frame.template.service.basic.security.domain.User;

import java.util.HashMap;

public interface LoginService {
    HashMap<String,Object> login(User user);
}
