package com.hao.test.year.demo2024.demo5;

import java.util.Arrays;

/**
 * @author xu.liang
 * @since 2024/5/11 09:25
 */
public class DemoTest {


    public static void main(String[] args) {

        String str = "asd\n";

        str = str.trim();

        System.out.println("str = " + str);


        String cooperateOrgIds = "11,22";
        String cooperateOrgNames = "这是11,这是22";
        String[] orgIds = cooperateOrgIds.split(",");
        System.out.println("orgIds = " + Arrays.toString(orgIds));
        String[] orgNames = cooperateOrgNames.split(",");
        System.out.println("orgIds.length = " + orgIds.length);
        for (int i = 0; i < orgIds.length; i++) {
            System.out.println("orgId = " + orgIds[i]);
            System.out.println("orgName = " + orgNames[i]);
        }


        String testStr = "comments@@notNull@@dataType@@length@@columnName@@primaryKey";
        String[] split = testStr.split("@@");
        System.out.println("split[0] = " + split[0]);
        System.out.println("split[1] = " + split[1]);
        System.out.println("split[4] = " + split[4]);
        System.out.println("split[5] = " + split[5]);
    }


}
