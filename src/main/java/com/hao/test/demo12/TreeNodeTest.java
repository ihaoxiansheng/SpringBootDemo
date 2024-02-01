package com.hao.test.demo12;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu.liang
 * @since 2023/12/20 11:31
 */
@Data
public class TreeNodeTest implements TreeVo<TreeNodeTest> {
    private String id;
    private String value;
    private String name;
    private String parentId;
    private String parentName;
    private List<TreeNodeTest> children;

    public TreeNodeTest(String id, String value, String parentId) {
        this.id = id;
        this.value = value;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

    public TreeNodeTest(String id, String value, String parentId, String parentName) {
        this.id = id;
        this.value = value;
        this.parentId = parentId;
        this.parentName = parentName;
        this.children = new ArrayList<>();
    }

    public TreeNodeTest(String id, String value) {
        this.id = id;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public TreeNodeTest(String id, String value, String name, String parentId, String parentName) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
        this.children = new ArrayList<>();
    }
}
