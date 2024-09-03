package com.hao.test.year.demo2024.demo3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * stream流的扁平化flatMap
 *
 * @author xu.liang
 * @since 2024/3/26 16:46
 */
public class SteamFlatMapTest {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("People", "who", "don't", "wan", "to", "be", "slaves");
        List<String> collect1 = words.stream().map(x -> x.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        // 输出[P, e, o, p, l, e, w, h, o, d, o, n, ', t, w, a, n, t, o, b, e, s, l, a, v, e, s]
        System.out.println(collect1);

        List<String> n1 = Arrays.asList("a", "b", "c");
        List<String> n2 = Arrays.asList("1", "2", "3");
        // 输出
        // a,1
        // a,2
        // a,3
        // b,1
        // b,2
        // b,3
        // c,1
        // c,2
        // c,3
        List<String[]> collect2 = n1.stream().flatMap(i -> n2.stream().map(j -> new String[]{i, j})).collect(Collectors.toList());
        collect2.stream().forEach(x -> System.out.println(x[0] + "," + x[1]));

    }

}
