package com.gstdev.cloud.service.system;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeBuilder {
    // 构建树的方法
    public static <T> List<TreeNode<T>> buildTree(List<T> items, Function<T, Object> idGetter, Function<T, Object> pidGetter, Comparator<T> comparator) {
        // 用于存储所有节点的映射关系
        Map<Object, TreeNode<T>> nodeMap = new HashMap<>();
        // 用于存储根节点列表
        List<TreeNode<T>> rootNodes = new ArrayList<>();

        // 创建所有节点，并将其存储在 nodeMap 中
        for (T item : items) {
            TreeNode<T> node = new TreeNode<>(item);
            nodeMap.put(idGetter.apply(item), node);
        }

        // 根据 pid 建立父子关系
        for (T item : items) {
            // 获取父节点 ID
            Object pid = pidGetter.apply(item);
            // 获取当前节点
            TreeNode<T> node = nodeMap.get(idGetter.apply(item));

            // 如果 pid 为空或 nodeMap 中没有对应的父节点，则认为是根节点
            if (pid == null || !nodeMap.containsKey(pid)) {
                // 将当前节点加入根节点列表
                rootNodes.add(node);
            } else {
                // 将当前节点加入其父节点的子节点列表
                nodeMap.get(pid).addChild(node);
            }
        }

        // 对所有节点的子节点进行排序
        for (TreeNode<T> node : nodeMap.values()) {
            node.setChildren(node.getChildren().stream().sorted((o1, o2) -> comparator.compare(o1.getData(), o2.getData())).collect(Collectors.toList()));
        }

        // 对根节点列表进行排序并返回
        return rootNodes.stream().sorted((o1, o2) -> comparator.compare(o1.getData(), o2.getData())).collect(Collectors.toList());
    }
}
