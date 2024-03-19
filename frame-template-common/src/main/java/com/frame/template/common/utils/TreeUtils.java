package com.frame.template.common.utils;


import com.frame.template.common.domain.Treeify;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 树工具类
 *
 * @author zhucy
 */
public class TreeUtils {

  private TreeUtils() {

  }

  /**
   * 构建树
   *
   * @param list 树节点列表
   * @return 树
   */
  public static <T extends Treeify<T, ID>, ID> List<T> buildTree(List<T> list) {
    Map<ID, T> nodeMap = new LinkedHashMap<>();
    for (T item : list) {
      if (null == item) {
        continue;
      }
      nodeMap.put(item.getId(), item);
    }
    List<T> topNodes = new ArrayList<>();
    for (T treeNode : nodeMap.values()) {
      if (nodeMap.containsKey(treeNode.getParentId())) {
        T parent = nodeMap.get(treeNode.getParentId());
        if (null == parent.getChildren()) {
          parent.setChildren(new ArrayList<>());
        }
        parent.getChildren().add(treeNode);
      } else {
        topNodes.add(treeNode);
      }
    }
    return topNodes;
  }
}
