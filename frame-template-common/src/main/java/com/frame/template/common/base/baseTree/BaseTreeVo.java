package com.frame.template.common.base.baseTree;

import com.frame.template.common.base.BaseVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseTreeVo<V> extends BaseVo {


  List<V> children;

}
