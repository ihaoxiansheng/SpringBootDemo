package com.hao.test.year.demo2023.demo2.ListToTree.tree2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * List转为树型结构对象
 *
 * @author xu.liang
 * @since 2023/2/27 09:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    /**
     * 节点Id
     */
    private String id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 父节点Id
     */
    private String parentId;

    /**
     * 子节点集合
     */
    private List<TreeNode> children;

}
