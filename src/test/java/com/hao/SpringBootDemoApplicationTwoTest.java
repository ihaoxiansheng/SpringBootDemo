package com.hao;

import com.hao.dao.UserDao;
import com.hao.entity.User;
import com.hao.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试类2
 *
 * @author xu.liang
 * @since 2024/2/20 14:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@Slf4j
public class SpringBootDemoApplicationTwoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void baseTest() {
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
