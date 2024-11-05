package com.hao.test.year.demo2024.demo9;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.hao.entity.User;
import com.hao.entity.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xu.liang
 * @since 2024/9/2 09:54
 */
public class DemoTest {

    public static void main(String[] args) {

        User user = new User();
        user.setId("1");
        user.setName("2");
        user.setAge("3");
        user.setEmail("4");
        user.setUserInfo(new UserInfo());
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = BeanUtils.beanToMap(user);
        System.out.println("map = " + map);
        list.add(map);
        List<User> userList = BeanUtils.mapsToBeans(list, User.class);
        System.out.println("userList = " + userList);

        User bean = BeanUtil.toBean(map, User.class);
        System.out.println("bean = " + bean);
        User user1 = BeanUtil.mapToBean(map, User.class, false, new CopyOptions());
        System.out.println("user1 = " + user1);
        Map<String, Object> map1 = BeanUtil.beanToMap(user1);
        System.out.println("map1 = " + map1);

        System.out.println("System.getProperty(\"os.name\") = " + System.getProperty("os.name"));

        // hutool 高并发测试-ConcurrencyTester：简单模拟N个线程调用某个业务测试其并发状况
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
            // 测试的逻辑内容
            long delay = RandomUtil.randomLong(100, 1000);
            ThreadUtil.sleep(delay);
            // Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
        });
        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());


        String aaa = "aaa->消费处->中国极限云网运营部->中国极限";
        String[] split = aaa.split("->");
        int targetIndex = aaa.indexOf("中国极限云网运营部");
        System.out.println("targetIndex = " + targetIndex);
        int lastArrowIndex = aaa.lastIndexOf("->", targetIndex - 1);
        System.out.println("lastArrowIndex = " + lastArrowIndex);
        String previousLevel = (lastArrowIndex != -1)
                ? aaa.substring(lastArrowIndex + 2, targetIndex)
                : aaa.substring(0, targetIndex);
        System.out.println("previousLevel = " + previousLevel);

        int i1 = aaa.indexOf("->", aaa.indexOf("->", targetIndex - 2));
        System.out.println("i1 = " + i1);
        System.out.println("cloudNetIndex = " + targetIndex);
        System.out.println("split.length = " + split.length);
        System.out.println("split[0] = " + split[0]);

        String a1 = "a1.";

        String[] split1 = a1.split("\\.");
        System.out.println("split1.length = " + split1.length);
        StringBuilder reversedStr = new StringBuilder();
        // 倒序遍历数组
        for (int i = split1.length - 1; i >= 0; i--) {
            reversedStr.append(split1[i]);
            if (i != 0) {
                // 不是最后一个时，添加"->"
                reversedStr.append("->");
            }
        }
        System.out.println("reversedStr = " + reversedStr);


        String ownerOrgName2 = "承载网室->ICNOC（上海）->中国电信集团有限公司云网运营部->中国电信集团有限公司总部及直属单位";
        int cloudNetIndex = ownerOrgName2.indexOf("中国电信集团有限公司云网运营部");
        String[] split2 = ownerOrgName2.split("->");
        System.out.println("split2.length = " + split2.length);
        System.out.println("split2[0] = " + split2[0]);
        int lastArrowIndex1 = ownerOrgName2.lastIndexOf("->", cloudNetIndex);
        String threeOrgName = "";
        if (lastArrowIndex1 != -1) {
            threeOrgName = ownerOrgName2.substring(0, lastArrowIndex1);
            if (threeOrgName.contains("->")) {
                threeOrgName = threeOrgName.substring(threeOrgName.lastIndexOf("->") + 2);
            }
        }
        System.out.println("threeOrgName = " + threeOrgName);
        System.out.println("split2 = " + Arrays.toString(split2));

        String[] sss = new String[]{"a", "b"};
        for (int i = 0; i < sss.length; i++) {
            System.out.println("sss[i] = " + sss[i]);
            System.out.println("sss[i - 1] = " + (i > 0 ? sss[i - 1] : sss[i]));
        }

        String ownerOrgName3 = "承载网室->ICNOC（上海）->中国电信集团有限公司云网运营部->中国电信集团有限公司总部及直属单位";
        String[] split3 = ownerOrgName3.split("->");
        System.out.println("split3 = " + Arrays.toString(split3));
        for (int i = 0; i < split3.length; i++) {
            if ("服务调度室（GCSC）".equals(split3[0])) {
                threeOrgName = "ISOC";
                break;
            } else if ("中国电信集团有限公司云网运营部".equals(split3[i])) {
                // 获取机构序列中包含 中国电信集团有限公司云网运营部 的前一级
                threeOrgName = i > 0 ? split3[i - 1] : split3[0];
                break;
            }
        }
        System.out.println("threeOrgName = " + threeOrgName);

    }
}
