package com.hao.springbootdemo.test.demo2.ListToTree.tree1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xu.liang
 * @since 2023/3/9 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeEntity implements TreeVo<TreeEntity> {

    private String id;

    private String name;

    private String parentId;

    private String parentName;

    private List<TreeEntity> children;

}
