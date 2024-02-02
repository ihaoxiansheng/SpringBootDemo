package com.hao.test.year.demo2023.demo12;

import java.util.List;

/**
 * @author xu.liang
 * @since 2023/12/20 11:31
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
