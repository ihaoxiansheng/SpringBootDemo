package com.hao.test;

import com.hao.dao.UserDao;
import com.hao.entity.User;
import com.hao.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xu.liang
 * @since 2023/8/14 16:28
 */

//@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MybatisPlusTestTest {

    @Resource
    private UserDao userDao;

    @Test
    void testInsert() {
        log.info("============================================================");
        User user = new User();
        user.setName("你好");
        user.setAge("11");
        user.setEmail("123@qq.com");
        user.setUserInfo(new UserInfo());
        userDao.insert(user);
        System.out.println("user.getId() = " + user.getId());
    }

}
