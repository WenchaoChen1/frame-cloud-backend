package com.gstdev.cloud.service.system.pojo.vo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveInput {
    String username;
    String mobile;
    String email;
    String avatar;
    Integer gender;
    String password;
}
