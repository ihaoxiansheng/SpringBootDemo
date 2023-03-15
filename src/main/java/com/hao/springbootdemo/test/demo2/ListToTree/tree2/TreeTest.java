package com.hao.springbootdemo.test.demo2.ListToTree.tree2;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xu.liang
 * @since 2023/2/25 11:33
 */
public class TreeTest {
    public static void main(String[] args) {

        List<TreeNode> list = new ArrayList<>();
        list.add(new TreeNode("370000", "山东省", "0", null));
        list.add(new TreeNode("370100", "济南市", "370000", null));
        list.add(new TreeNode("370200", "青岛市", "370000", null));
        list.add(new TreeNode("370300", "淄博市", "370000", null));
        list.add(new TreeNode("371300", "临沂市", "370000", null));
        list.add(new TreeNode("370102", "历下区", "370100", null));
        list.add(new TreeNode("370103", "市中区", "370100", null));
        list.add(new TreeNode("370202", "市南区", "370200", null));
        // 使用list转换
        List<TreeNode> treeList = forMethod(list);
        List<TreeNode> treeList1 = tranTreeUseList(list, "0");
        List<TreeNode> treeList2 = tranTreeUseStream(list, "0");


        System.out.println(treeList);
        System.out.println(treeList1);
        System.out.println(treeList2);


    }

    /**
     * 双重for循环方法转换成树形结构
     */
    public static List<TreeNode> forMethod(List<TreeNode> treeList) {
        List<TreeNode> rootTree = new ArrayList<>();
        for (TreeNode tree : treeList) {
            // 第一步 筛选出最顶级的父节点
            if ("0".equals(tree.getParentId())) {
                rootTree.add(tree);
            }
            // 第二步 筛选出该父节点下的所有子节点列表
            for (TreeNode node : treeList) {
                if (node.getParentId().equals(tree.getId())) {
                    if (CollectionUtils.isEmpty(tree.getChildren())) {
                        tree.setChildren(new ArrayList<>());
                    }
                    tree.getChildren().add(node);
                }
            }
        }
        return rootTree;
    }

    /**
     * 方式一：使用foreach转换
     */
    public static List<TreeNode> tranTreeUseList(List<TreeNode> treeList, String id) {
        // 收集传递的集合中父id相同的TreeNode
        List<TreeNode> children = new ArrayList<>();
        for (TreeNode treeNode : treeList) {
            // 判断该节点的父id，是否与传入的父id相同，相同则递归设置其孩子节点，并将该节点放入children集合中
            if (treeNode.getParentId().equals(id)) {
                // 递归设置其孩子节点
                treeNode.setChildren(tranTreeUseList(treeList, treeNode.getId()));
                // 放入children集合
                children.add(treeNode);
            }
        }
        return children;
    }

    /**
     * 方式二：使用stream流转换
     */
    public static List<TreeNode> tranTreeUseStream(List<TreeNode> treeList, String id) {
        List<TreeNode> list = treeList.stream()
                // 过滤父节点与传递的id相同的TreeNode对象
                .filter(treeNode -> id.equals(treeNode.getParentId()))
                .map(treeNode -> {
                    // 递归设置孩子节点
                    treeNode.setChildren(tranTreeUseStream(treeList, treeNode.getId()));
                    return treeNode;
                }).collect(Collectors.toList());
        return list;
    }

}
