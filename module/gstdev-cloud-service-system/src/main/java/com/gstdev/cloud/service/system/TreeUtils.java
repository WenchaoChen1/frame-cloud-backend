package com.gstdev.cloud.service.system;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeUtils {
    // 构建树的方法
    public static <ID, T extends TreeNode<ID, T>> List<T> buildTree(
        List<T> items,
        Function<T, ID> idGetter,
        Function<T, ID> pidGetter,
        Comparator<T> comparator) {

        // 用于存储所有节点的映射关系
        Map<ID, T> nodeMap = items.stream()
            .collect(Collectors.toConcurrentMap(idGetter, Function.identity()));

        // 用于存储根节点列表
        List<T> rootNodes = new CopyOnWriteArrayList<>();

        // 根据 pid 建立父子关系
        items.parallelStream().forEach(item -> {
            ID pid = pidGetter.apply(item);
            T node = nodeMap.get(idGetter.apply(item));
            if (pid == null || !nodeMap.containsKey(pid)) {
                rootNodes.add(node);
            } else {
                nodeMap.get(pid).addChild(node);
            }
        });
        // 如果 comparator 不为 null，则对所有节点的子节点进行排序
        if (comparator != null) {
            nodeMap.values().parallelStream().forEach(node -> {
                node.setChildren(node.getChildren().stream().sorted(comparator).collect(Collectors.toList()));
            });

            // 对根节点列表进行排序
            rootNodes.sort(comparator);
        }

        // 对根节点列表返回
        return rootNodes;
    }
}