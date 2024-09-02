package com.hao.test.year.demo2024.demo8;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.SneakyThrows;

/**
 * @author xu.liang
 * @since 2024/8/29 10:20
 */
public class DruidTest {

    @SneakyThrows
    public static void main(String[] args) {


        String decrypt = ConfigTools.decrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALiWqn7cSMz21+LdZ9JOFc4OEyA4oazVJtVidr9gbPwVLdI4NvKzUIDLX2DCBTVc+1ch4pstaGutA+toj9MW5RsCAwEAAQ==", "VCFrtpMrbFeU+XBjRa1rP2j3ALpy4+AOqb6Fwc4dSTDCn8slfHEc6zOXFL1/79SJVYpMj4fjsiVP22IsT+fhZg==");
        System.out.println("decrypt = " + decrypt);


    }
}
