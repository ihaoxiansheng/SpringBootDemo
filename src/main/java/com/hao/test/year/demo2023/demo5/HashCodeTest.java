package com.hao.test.year.demo2023.demo5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author xu.liang
 * @since 2023/5/11 14:47
 */
public class HashCodeTest {
    public static void main(String[] args) {
        Cat cat1 = new Cat("黑色", "鲁鲁", 2);
        Cat cat2 = new Cat("白色", "鲁鲁", 2);

        System.out.println(cat1.equals(cat2)); // true  callSuper = false
//        System.out.println(cat1.equals(cat2)); // false callSuper = true
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Animal {
    private String color;
}

@Data
@EqualsAndHashCode(callSuper = false) // 排除父类字段
class Cat extends Animal {
    private String name;
    private Integer age;

    public Cat(String color, String name, Integer age) {
        super(color);
        this.name = name;
        this.age = age;
    }
}
