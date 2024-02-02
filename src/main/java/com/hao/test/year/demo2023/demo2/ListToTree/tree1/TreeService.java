package com.hao.test.year.demo2023.demo2.ListToTree.tree1;

import java.util.List;

/**
 * @author xu.liang
 * @since 2023/2/27 09:18
 */
public interface TreeService {

    /**
     * List转为树型结构
     *
     * @param voList
     */
    void transToTree(List<? extends TreeVo<?>> voList);

}
