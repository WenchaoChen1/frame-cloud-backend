package com.frame.template.autoconfigure.service.identity.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 */
@Getter
@Setter
public class IdentitySaveDto {
    private String password;

    private String username;

    private String email;
    private String userId;

    public void setId(String id) {
        this.userId = id;
    }
}
