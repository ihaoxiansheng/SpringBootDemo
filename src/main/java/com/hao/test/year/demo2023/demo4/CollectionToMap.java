package com.hao.test.year.demo2023.demo4;

import com.hao.entity.User;
import com.hao.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 把两个表内的数据查出来放进一个List，处理对应关系，以前我们的写法是下面这种，用了一个嵌套循环的形式，判断一下userId是否相等，如果相等，则赋值
 *
 * @author xu.liang
 * @since 2023/4/22 14:40
 */
public class CollectionToMap {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId("1");
        user1.setName("张三");

        User user2 = new User();
        user2.setId("2");
        user2.setName("李四");

        userList.add(user1);
        userList.add(user2);


        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId("1");
        userInfo1.setAge("20");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserId("2");
        userInfo2.setAge("21");

        userInfoList.add(userInfo1);
        userInfoList.add(userInfo2);

//        // 查询用户
//        List<User> userList = userDao.selectUser();
//        // 查询用户信息
//        List<UserInfo> userInfoList = userInfoDao.selectUserInfo();
        // 遍历赋值
        userList = userList.stream().peek(user -> {
            // 遍历userInfo
            userInfoList.forEach(userInfo -> {
                // 如果user的id和userInfo相等，则赋值
                if (user.getId().equals(userInfo.getUserId())) {
                    user.setUserInfo(userInfo);
                }
            });
        }).collect(Collectors.toList());

        userList.forEach(System.out::println);

        // 现在用了toMap，原先嵌套循环的o(n2)的时间复杂度，现在变成了o(n)。只用循环遍历一次，就能达到我们的效果，非常实用~学会的小伙伴赶紧拿去秀操作吧！
//    // 查询用户
//    List<User> userList = userDao.selectUser();
//    // 查询用户信息
//    List<UserInfo> userInfoList = userInfoDao.selectUserInfo();

        // 转换为map，key为userId，value为userInfo
        Map<String, UserInfo> userInfoMap = userInfoList.stream().collect(Collectors.toMap(UserInfo::getUserId, Function.identity(), (key1, key2) -> key2));
        //批量赋值
        userList = userList.stream().peek(user -> {
            //直接赋值
            user.setUserInfo(userInfoMap.get(user.getId()));
        }).collect(Collectors.toList());
        //打印
        userList.forEach(System.out::println);

    }

}
