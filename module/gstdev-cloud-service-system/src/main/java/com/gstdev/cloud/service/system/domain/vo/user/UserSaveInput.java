package com.gstdev.cloud.service.system.domain.vo.user;

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
