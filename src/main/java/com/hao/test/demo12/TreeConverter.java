package com.hao.test.demo12;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu.liang
 * @since 2023/12/20 11:27
 */
public class TreeConverter {

    public static List<TreeNodeTest> convertToTree(List<Map<String, Object>> flatList) {
        Map<String, TreeNodeTest> nodeMap = new HashMap<>();

        // Create nodes and store them in the map
        for (Map<String, Object> item : flatList) {
            String nodeId = item.get("id").toString();
            String nodeValue = item.get("value").toString();
            String parentId = String.valueOf(item.get("parent_id"));

            TreeNodeTest node = new TreeNodeTest(nodeId, nodeValue);
            nodeMap.put(nodeId, node);

            // Check if it has a parent node
            if (nodeMap.containsKey(parentId)) {
                TreeNodeTest parentNode = nodeMap.get(parentId);
                parentNode.getChildren().add(node);
            }
        }

        // Retrieve root node(s)
        List<TreeNodeTest> rootNodes = new ArrayList<>();
        for (TreeNodeTest node : nodeMap.values()) {
            if (CollUtil.isNotEmpty(node.getChildren())) {
                rootNodes.add(node);
            }
        }

        return rootNodes;

    }

    public static void convertToTree2(List<TreeNodeTest> flatList) {
        Map<String, TreeNodeTest> nodeMap = new HashMap<>();
        flatList.forEach((vox) -> nodeMap.put(vox.getId(), vox));
        for (TreeNodeTest item : flatList) {
            if ("0".equals(item.getParentId())) {
                continue;
            }
            TreeNodeTest parentVo = nodeMap.get(item.getParentId());
            if (parentVo == null) {
                continue;
            }
            item.setParentName(parentVo.getName());
            List<TreeNodeTest> children = parentVo.getChildren();
            if (children == null) {
                children = Lists.newArrayList();
                parentVo.setChildren(children);
            }
            children.add(item);
        }
    }

    public static void transToTree(List<? extends TreeVo> voList) {
        Map<String, TreeVo> voMap = Maps.newHashMap();
        voList.forEach(vo -> {
            voMap.put(vo.getId(), vo);
        });
        // 转为树
        for (TreeVo vo : voList) {
            if ("0".equals(vo.getParentId())) {
                continue;
            }
            TreeVo parentVo = voMap.get(vo.getParentId());
            if (parentVo == null) {
                continue;
            }
            vo.setParentName(parentVo.getName());
            List<TreeVo> children = parentVo.getChildren();
            if (children == null) {
                children = Lists.newArrayList();
                parentVo.setChildren(children);
            }
            children.add(vo);
        }
    }

    public static void main(String[] args) {
        List<Map<String, Object>> flatList = new ArrayList<>();

//        Map<String, Object> node1 = new HashMap<>();
//        node1.put("id", "1");
//        node1.put("value", "A");
//        node1.put("parent_id", "0");
//        flatList.add(node1);
//
//        Map<String, Object> node2 = new HashMap<>();
//        node2.put("id", "2");
//        node2.put("value", "B");
//        node2.put("parent_id", "1");
//        flatList.add(node2);
//
//        Map<String, Object> node3 = new HashMap<>();
//        node3.put("id", "3");
//        node3.put("value", "C");
//        node3.put("parent_id", "1");
//        flatList.add(node3);
//
//        Map<String, Object> node4 = new HashMap<>();
//        node4.put("id", "4");
//        node4.put("value", "D");
//        node4.put("parent_id", "2");
//        flatList.add(node4);
//
//        Map<String, Object> node5 = new HashMap<>();
//        node5.put("id", "5");
//        node5.put("value", "E");
//        node5.put("parent_id", "3");
//        flatList.add(node5);
//
//
//
//        List<TreeNodeTest> tree = convertToTree(flatList);
//        List<Map<String, Object>> tree1 = new ArrayList<>();
//        for (TreeNodeTest treeNodeTest : tree) {
//            JSONObject jsonObject = JSONUtil.parseObj(treeNodeTest);
//            tree1.add(jsonObject);
//        }
//        System.out.println("tree = " + tree);
//        System.out.println("tree1 = " + tree1);

        List<TreeNodeTest> flatList2 = new ArrayList<>();
        TreeNodeTest node11 = new TreeNodeTest("1", "AA", "A", "0", "这是0");
        TreeNodeTest node22 = new TreeNodeTest("2", "BB", "B", "1", "这是A");
        TreeNodeTest node33 = new TreeNodeTest("3", "CC", "C", "1", "这是A");
        TreeNodeTest node44 = new TreeNodeTest("4", "DD", "D", "2", "这是B");
        TreeNodeTest node55 = new TreeNodeTest("5", "EE", "E", "3", "这是C");
        flatList2.add(node11);
        flatList2.add(node22);
        flatList2.add(node33);
        flatList2.add(node44);
        flatList2.add(node55);
        convertToTree2(flatList2);
//        transToTree(flatList2);
        List<Map<String, Object>> tree22 = new ArrayList<>();
        for (TreeNodeTest treeNodeTest : flatList2) {
            JSONObject jsonObject = JSONUtil.parseObj(treeNodeTest);
            if ("0".equals(treeNodeTest.getParentId())) {
                tree22.add(jsonObject);
                break;
            }
        }
        System.out.println("tree2 = " + tree22);
    }
}
