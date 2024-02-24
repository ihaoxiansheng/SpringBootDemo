package com.hao.test.year.demo2024.demo2;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云sms发送短信，开通教程：<a href="https://help.aliyun.com/zh/sms/getting-started/get-started-with-sms">...</a>
 *
 * @author xu.liang
 * @since 2024/2/19 11:10
 */
@Slf4j
public class SmsUtil {
    public static boolean SendSms(String phoneNum, String template, String code) {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou",
                // accessKeyId
                "<你的accessKeyId>",
                // accessSecret
                "<你的accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        // 接收号码
        request.putQueryParameter("PhoneNumbers", phoneNum);
        // 短信签名（如某某科技有限公司）
        request.putQueryParameter("SignName", "<你的短信签名>");
        // 短信模板
        request.putQueryParameter("TemplateCode", template);
        // 验证码
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            map = JSONObject.parseObject(response.getData());
            System.out.println(map);
            code = (String) map.get("Code");
            if (!"OK".equals(code)) {
                return false;
            }
        } catch (ClientException e) {
            log.error("发送失败：", e);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean isSuccess = SendSms("<接收的电话号码>", "<短信模板>", "<验证码>");
        System.out.println(isSuccess);
    }
}
