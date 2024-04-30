package com.gstdev.cloud.service.system.pojo.vo.login;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: zcy
 * @date: 2022/12/7
 * @description:
 */
@Getter
@Setter
public class LoginInput {
    String identity;
    String password;
    String email;
    String mobile;
}
