package com.frame.template.service.system.pojo.vo.RAccountDepart;

import com.frame.template.service.system.pojo.entity.Account;
import com.frame.template.service.system.pojo.entity.Depart;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: 666
 * @date: 2022-12-06 11:31
 */
@Getter
@Setter
public class RAccountDepartSaveInput implements Serializable {
    private Account account;
    private Depart depart;
}
