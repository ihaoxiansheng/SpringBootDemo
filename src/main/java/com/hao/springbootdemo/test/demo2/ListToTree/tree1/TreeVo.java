package com.hao.springbootdemo.test.demo2.ListToTree.tree1;

import java.util.List;

/**
 * List转为树型结构demo
 *
 * @author xu.liang
 * @since 2023/2/27 09:11
 */
public interface TreeVo<T> {

    void setId(String id);

    String getId();

    void setParentId(String parentId);

    String getParentId();

    void setName(String name);

    String getName();

    void setParentName(String parentName);

    String getParentName();

    void setChildren(List<T> children);

    List<T> getChildren();

}
