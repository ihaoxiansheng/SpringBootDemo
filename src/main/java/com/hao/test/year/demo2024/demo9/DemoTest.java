package com.hao.test.year.demo2024.demo9;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.hao.entity.User;
import com.hao.entity.UserInfo;

import java.util.ArrayList;
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


    }
}
