package com.frame.template.common.domain;

import java.util.List;

/**
 * 树业务对象
 *
 * @author zhucy
 */
public interface Treeify<T extends Treeify<T, ID>, ID> {

    /**
     * 获取父id
     *
     * @return 父id
     */
    ID getParentId();

    /**
     * 获取孩子节点, 不允许为空
     *
     * @return 获取孩子节点
     */
    List<T> getChildren();

    /**
     * 写孩子节点
     *
     * @param list 孩子节点
     */
    void setChildren(List<T> list);

    /**
     * 获取当前对象id
     *
     * @return 当前对象id
     */
    ID getId();

}
