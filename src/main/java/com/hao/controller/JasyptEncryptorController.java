package com.hao.controller;

import io.swagger.annotations.Api;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController("/jasypt/test")
@Api(tags = "Jasypt加密")
public class JasyptEncryptorController {

    @Resource
    public StringEncryptor stringEncryptor;

    public static final String DECRYPT_FLAG = "HAO";

    @GetMapping("encrypt")
    public String encrypt(String message) {
        return stringEncryptor.encrypt(message);
    }

    @GetMapping("decrypt")
    public String decrypt(String encryptedMessage, String decryptFlag) {
        if (Objects.equals(DECRYPT_FLAG, decryptFlag)) {
            return stringEncryptor.decrypt(encryptedMessage);
        } else {
            return "无权限";
        }
    }

}
