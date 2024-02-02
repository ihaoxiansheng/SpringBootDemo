package com.hao.test.year.demo2023.demo1;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 脱敏desensitization
 *
 * @author xu.liang
 * @since 2022/10/17 16:05
 */
public class DeSenUtils1017 {
    private final static Logger log = LoggerFactory.getLogger(DeSenUtils1017.class);

    private static Pattern deSenBodyPattern;
    private static Pattern deSenBodyPatternRequest;

    public DeSenUtils1017() {
        List<String> deSenBodyList = Lists.newArrayList("geetestValidate", "certificateNo",
                "verifyCode", "loginPwd", "token", "privateKey", "publicKey", "googleSecret", "oldSmsCode",
                "smsCode", "securityPwd", "verifyCode1", "verifyCode2", "newPwd", "pubKey", "channelToken",
                "totalUsdAsset", "totalUsdtAsset", "usdAsset", "usdtAsset", "availableAmount", "frozenAmount",
                "totalAmount", "usdConvertedAmount", "amount", "feeAmount", "password", "accessKey", "secretKey", "geetestValidate");
        deSenBodyPattern = buildPattern(deSenBodyList);
        deSenBodyList.addAll(ImmutableList.of("code"));
        deSenBodyPatternRequest = buildPattern(deSenBodyList);

    }

    private Pattern buildPattern(List<String> deSenBodyList) {
        String deSenBodyString = deSenBodyList.stream().collect(Collectors.joining("|", "\\s*(", ")\\s*"));
        return Pattern.compile(deSenBodyString + "\":(\")?([\\w:\\+=/\\s]+)", Pattern.CASE_INSENSITIVE);
    }

    private static DeSenUtils1017 INSTANCE = new DeSenUtils1017();

    /**
     * 脱敏
     */
    public static String deSenBody(String body) {
        return INSTANCE.deSen(body, deSenBodyPattern);
    }

    /**
     * 请求脱敏
     */
    public static String deSenBodyByRequest(String body) {
        return INSTANCE.deSen(body, deSenBodyPatternRequest);
    }


    private String deSen(String body, Pattern patter) {
        try {
            Matcher matcher = patter.matcher(body);
            while (matcher.find()) {
                String target = matcher.group(3);
                String desc = target.replaceAll("(.).*(.)", "$1***$2");
                body = body.replace(target, desc);
            }
        } catch (Exception e) {
            log.warn("脱敏异常请求体或响应体异常", e);
        }
        return body;
    }

    public static void main(String[] args) {
        String str = "{\"costTime\":162,\"header\":\"{\"devicesource\":\"native\",\"lang\":\"en-US\",\"deviceid\":\"A5A6F0c6B90638A2F-e195d43830A5e9979906e5A0A8A-9330A0B3ADBBB9d93-AFF5dBcF9-A4c749-AB10-4EB49EABF9E7-85315174-34961239\",\"user_no\":\"1651274403173142529\"}\",\"method\":\"POST\",\"path\":\"/app/cb/user/withdraw/submit/withdraw\",\"requestBody\":\"{\"currency\":\"KOFO\",\"walletAddress\":\"0x863b065db8f23f34feedb2c836ede0e38b9c2e6f\",\"labelContent\":null,\"amount\":\"200012\",\"securityPwd\":\"fasdfjkhsjdfhi123\",\"verifyCode1\":\"198231\",\"verifyCode2\":\"\"}\",\"responseBody\":\"{\"msg\":\"success\",\"traceId\":\"574a720a9f6db51f\",\"code\":\"000000\",\"success\":true}\",\"status\":200,\"userNo\":\"1651274403173142529\",\"code\":\"123456\"}";

        System.out.println(deSenBody(str));

        System.out.println(deSenBodyByRequest(str));
    }
}
