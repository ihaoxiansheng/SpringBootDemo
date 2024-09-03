package com.hao.test.year.demo2023.demo2.ListToTree.tree1;

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
public class TreeEntity implements TreeVO<TreeEntity> {

    private String id;

    private String name;

    private String parentId;

    private String parentName;

    private List<TreeEntity> children;

}
