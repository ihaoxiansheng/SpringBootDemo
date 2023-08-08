package com.hao.test.demo7;
import com.hao.entity.UserInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.hao.entity.User;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu.liang
 * @since 2023/7/17 09:57
 */
public class DemoTest {

    public static void main(String[] args) {

//        String sheetStreamNo = "2305110046SZ7400";
//        long l = Long.parseLong(sheetStreamNo);
//        System.out.println("l = " + l);

        List<String> fileIdList = new ArrayList<>();
        fileIdList.add("1");
        fileIdList.add("2");
        System.out.println("fileIdList = " + fileIdList.toString());
        System.out.println("fileIdList2 = " + String.join(",", fileIdList));

//        testMutableTriple();
//        testSerializationUtils();

        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("当前时间的毫秒数： " + currentTimeMillis);
        long l = System.nanoTime();
        System.out.println("当前时间的毫秒数： " + l);


        User user1 = new User();
        user1.setId("1");
        user1.setName("11");
        user1.setAge("111");
        user1.setEmail(null);
        user1.setUserInfo(new UserInfo());

        User user2 = new User();
        user2.setId("2");
        user2.setName("22");
        user2.setAge("222");
        user2.setEmail("2222");
        user2.setUserInfo(new UserInfo());

        // A复制给B A对象中的null不覆盖B中对象的值
        BeanUtil.copyProperties(user1, user2, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        System.out.println("user2 = " + user2);

        String num = "123456789";
        String sub = num.substring(0, 2);
        System.out.println("sub = " + sub);
        String substring = num.substring(num.length() - 5);
        System.out.println("substring = " + substring);


    }

    /**
     * 就是提供了支持返回多个元素的一些类
     * MutableTriple对象是可以比较的
     * 元组和列表list一样，都可能用于数据存储，包含多个数据；但是和列表不同的是：列表只能存储相同的数据类型，
     * 而元组不一样，它可以存储不同的数据类型，比如同时存储int、string、list等，并且可以根据需求无限扩展。
     * 当在一个方法中，你需要返回几个对象，这几个对象的类型一致，你可以返回一个数组；如果几个对象的类型不同呢，当然你可以返回一个Object[]数组，可是这样在使用结果数组的时候，就需要强转对象的类型，会导致类型不安全；
     * 也可以定义一个dto，当多个场景下需要使用的不同，需要定义多个dto，这样会类爆炸，而且重用率极低；
     * 在非常接近Java语言的Scala里有元组的定义：val t = (1, 3.14, "Fred")，就是一个不同类型的数据放到一个线性集合里，在Java里我们可以模拟出一个类似的结构，以适合上面的场景
     */
    public static void testMutableTriple() {
        MutableTriple<String, Integer, Boolean> triple = new MutableTriple<>();
        triple.setLeft("1");
        triple.setMiddle(2);
        triple.setRight(false);
        System.out.println("triple = " + triple);
        System.out.println("triple.getLeft = " + triple.getLeft());
        System.out.println("triple.getMiddle = " + triple.getMiddle());
        System.out.println("triple.getRight = " + triple.getRight());

        MutablePair<String, Integer> monday = new MutablePair<String, Integer>("星期一", 0);
        System.out.println("monday.getLeft() = " + monday.getLeft());
        System.out.println("monday.getRight() = " + monday.getRight());
        MutableTriple<String, String, String> student = new MutableTriple<String, String, String>("郭靖", "黄蓉", "周伯通");
        System.out.println("student.getLeft() = " + student.getLeft());
    }


    /**
     * 增强的功能：深拷贝，方便的反序列化Deserialize，序列化Serialize等。
     */
    public static void testSerializationUtils(){
        User user1 = new User();
        user1.setName("第二中学");
        user1.setAge("22");
        User user2 = SerializationUtils.clone(user1);
        System.out.println("user1 = " + user1);
        System.out.println("user2 = " + user2);
        System.out.println("user1.equals(user2) = " + user1.equals(user2));
        System.out.println(user1 == user2);
    }


}
