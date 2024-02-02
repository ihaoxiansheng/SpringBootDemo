package com.hao.test.year.demo2023.demo1;


import com.hao.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liang
 */
public class Hello {
    public static void main(String[] args) {

        System.out.println("hello world");

//        String a = "abc_def_Ghi";
//        // 不包含本身位置
//        String str4 = a.substring(0,a.indexOf("_",a.indexOf("_") + 1));
//        // abc_def
//        System.out.println("第二个_前面字符串为：" + str4 );

        String url = "jdbc:p6spy:mysql://192.168.80.169:3306/ofm_formlowcode_db?allowMultiQueries=true&useUnicode" +
                "=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=CTT";

        String str = url.substring(url.indexOf("/", url.indexOf("/", url.indexOf("/") + 1) + 1) + 1, url.indexOf("?"));
        System.out.println("str：" + str);
        System.out.println("==========================================");

        // 初始化
        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student(20160001L, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002L, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003L, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004L, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001L, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002L, "元直", 23, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003L, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001L, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002L, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001L, "丁奉", 24, 5, "土木工程", "南京大学"));
            }
        };

        List<Long> collect = students.stream().map(r -> r.getId()).collect(Collectors.toList());
        System.out.println("collect = " + collect);

        // 2.1 过滤 顾名思义就是按照给定的要求对集合进行筛选满足条件的元素，java8提供的筛选操作包括：filter、distinct、limit、skip。

        // filter
        // 从集合students中筛选出所有武汉大学的学生
        List<Student> whuStudents = students.stream()
                .filter(student -> "武汉大学".equals(student.getSchool()))
                .collect(Collectors.toList());
        for (Student whuStudent : whuStudents) {
            System.out.println("whuStudent = " + whuStudent);
        }

//        // distinct:distinct操作类似于我们在写SQL语句时，添加的DISTINCT关键字，用于去重处理，distinct基于Object.equals(Object)实现
//        // 筛选出所有不重复的偶数
//        List<Integer> evens = nums.stream()
//                .filter(num -> num % 2 == 0).distinct()
//                .collect(Collectors.toList());

        // limit:limit操作也类似于SQL语句中的LIMIT关键字，不过相对功能较弱，limit返回包含前n个元素的流，当集合大小小于n时，则返回实际长度
        // 返回前两个专业为土木工程专业的学生
        List<Student> civilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).limit(2)
                .collect(Collectors.toList());
        System.out.println("civilStudents = " + civilStudents);

        // sort
        // 筛选出专业为土木工程的学生，并按年龄从小到大排序，筛选出年龄最小的两个学生
        List<Student> sortedCivilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).sorted((s1, s2) -> s1.getAge() - s2.getAge())
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("sortedCivilStudents = " + sortedCivilStudents);
        // 或者
        List<Student> sortedCivilStudents1 = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).sorted(Comparator.comparingInt(Student::getAge)).limit(2).collect(Collectors.toList());
        System.out.println("sortedCivilStudents1 = " + sortedCivilStudents1);
        // 如果是2个字段的差值比较：可以类似下面这种写法：
        // list.stream().sorted((s1, s2) -> (s1.getAge1()-s1.getAge() - (s2.getAge1() - s2.getAge()))).collect(Collectors.toList()))


        // skip:skip操作与limit操作相反，如同其字面意思一样，是跳过前n个元素
        List<Student> civilStudents2 = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .skip(2)
                .collect(Collectors.toList());
        System.out.println("civilStudents2 = " + civilStudents2);


        // 2.2 映射 在SQL中，借助SELECT关键字后面添加需要的字段名称，可以仅输出我们需要的字段数据，而流式处理的映射操作也是实现这一目的，在java8的流式处理中，主要包含两类映射操作：map和flatMap。
        // map
        List<String> names = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getName).collect(Collectors.toList());
        System.out.println("names = " + names);

        // 计算所有专业为计算机科学学生的年龄之和
        int totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();

        System.out.println("totalAge = " + totalAge);

        // flatMap
        // flatMap与map的区别在于 flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流。
        // 举例说明，假设我们有一个字符串数组String[] strs = {"java8", "is", "easy", "to", "use"};
        // 我们希望输出构成这一数组的所有非重复字符，那么我们可能首先会想到如下实现：
        String[] strs = {"java8", "is", "easy", "to", "use"};
        List<String[]> distinctStrs1 = Arrays.stream(strs)
                // 映射成为Stream<String[]>
                .map(str1 -> str1.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinctStrs1 = " + distinctStrs1);
        // 在执行map操作以后，我们得到是一个包含多个字符串（构成一个字符串的字符数组）的流，此时执行distinct操作是基于在这些字符串数组之间的对比，所以达不到我们希望的目的，此时的输出为：
        //[j, a, v, a, 8]
        //[i, s]
        //[e, a, s, y]
        //[t, o]
        //[u, s, e]

        // distinct只有对于一个包含多个字符的流进行操作才能达到我们的目的，即对Stream<String>进行操作。此时flatMap就可以达到我们的目的：

        List<String> distinctStrs2 = Arrays.stream(strs)
                // 映射成为Stream<String[]>
                .map(str2 -> str2.split(""))
                // 扁平化为Stream<String>
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinctStrs2 = " + distinctStrs2);

        // flatMap将由map映射得到的Stream<String[]>，转换成由各个字符串数组映射成的流Stream<String>，再将这些小的流扁平化成为一个由所有字符串构成的大流Steam<String>，从而能够达到我们的目的。
        // 与map类似，flatMap也提供了针对特定类型的映射操作：
        // flatMapToDouble(Function<? super T,? extends DoubleStream> mapper),
        // flatMapToInt(Function<? super T,? extends IntStream> mapper),
        // flatMapToLong(Function<? super T,? extends LongStream> mapper).

    }
}
