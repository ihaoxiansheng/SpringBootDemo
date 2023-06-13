package com.hao.test.demo2.ListToTree.tree1;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xu.liang
 * @since 2023/2/27 09:19
 */
@Service
public class TreeServiceImpl implements TreeService {

    public TreeServiceImpl() {
    }

    @Override
    public void transToTree(List<? extends TreeVo<?>> voList) {
        Map<String, TreeVo<?>> voMap = new HashMap<>();
        voList.forEach((vox) -> voMap.put(vox.getId(), vox));

        for (TreeVo<?> treeVo : voList) {
            if (!"0".equals(treeVo.getParentId())) {
                TreeVo<?> parentVo = voMap.get(treeVo.getParentId());
                if (parentVo != null) {
                    treeVo.setParentName(parentVo.getName());
                    List<TreeVo> children = (List<TreeVo>) parentVo.getChildren();
                    if (children == null) {
                        children = Lists.newArrayList();
                        parentVo.setChildren((List) children);
                    }

                    children.add(treeVo);
                }
            }
        }
    }

    @Test
    public void test() {

        List<TreeEntity> list = new ArrayList<>();
        list.add(new TreeEntity("370000", "山东省", "0", "", null));
        list.add(new TreeEntity("370100", "济南市", "370000", "", null));
        list.add(new TreeEntity("370200", "青岛市", "370000", "", null));
        list.add(new TreeEntity("370300", "淄博市", "370000", "", null));
        list.add(new TreeEntity("371300", "临沂市", "370000", "", null));
        list.add(new TreeEntity("370102", "历下区", "370100", "", null));
        list.add(new TreeEntity("370103", "市中区", "370100", "", null));
        list.add(new TreeEntity("370202", "市南区", "370200", "", null));

        transToTree(list);
        list.removeIf(t -> !"0".equals(t.getParentId()));
        System.out.println("list = " + list);

    }
}
