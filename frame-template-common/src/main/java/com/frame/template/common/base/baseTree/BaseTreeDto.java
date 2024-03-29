package com.frame.template.common.base.baseTree;

import com.frame.template.common.utils.treeUtils.TreeNode;
import com.frame.template.common.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
//public class BaseTreeDto<D extends BaseDto> extends BaseDto  {
public class BaseTreeDto<D extends BaseDto & TreeNode<String, D>> extends BaseDto implements TreeNode<String, D> {
 public List<D> children;
  String parentId;

  @Override
  public void addChild(D node) {
    if(children==null){
      children=new ArrayList<>();
    }
    children.add(node);
  }

  public void setChildren(List<D> children) {
    if(children==null){
      children=new ArrayList<>();
    }
    this.children = children;
  }
}
