package com.gstdev.cloud.service.system;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    // 节点数据
    private T data;
    // 子节点列表
    private List<TreeNode<T>> children = new ArrayList<>();

    // 构造函数，初始化节点数据
    public TreeNode(T data) {
        this.data = data;
    }

    // 获取节点数据
    public T getData() {
        return data;
    }

    // 获取子节点列表
    public List<TreeNode<T>> getChildren() {
        return children;
    }

    // 添加子节点
    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    // 设置子节点列表
    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }
}
