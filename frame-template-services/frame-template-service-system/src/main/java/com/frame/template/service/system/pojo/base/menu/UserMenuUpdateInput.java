// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.menu;

import com.frame.template.common.base.baseTree.BaseTreeUpdateInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserMenuUpdateInput extends BaseTreeUpdateInput {

  private String userId;

  private List<String> checkedMenuId;

  private String AccountType;
}

