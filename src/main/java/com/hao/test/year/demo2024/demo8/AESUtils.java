package com.hao.test.year.demo2024.demo8;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES 密码加密
 *
 * @version 1.0
 * @date 2023/2/25 16:48
 */
@Slf4j
public class AESUtils {

    private static final String encodeRules = "ofm";

    /**
     * 加密
     * 1.构造密钥生成器
     * 2.根据encode Rules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String AESEncode(String content) {
        try {
            // 1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 2.根据encode Rules规则初始化密钥生成器
            // 生成一个128位的随机源,根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);
            // 3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            // 4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            // 5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            // 6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes(StandardCharsets.UTF_8);
            // 9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            // 10.将加密后的数据转换为字符串
            // 这里用Base64Encoder中会找不到包
            // 解决办法：
            // 在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            // 11.将字符串返回
            return Base64.getEncoder().encodeToString(byte_AES);// new BASE64Encoder().encode();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            log.error("加密异常", e);
        }
        // 如果有错就返加null
        return null;
    }


    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(encryptKey.getBytes());
        keyGenerator.init(128, secureRandom);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDecode(String content) {
        try {
            // 1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 2.根据encode Rules规则初始化密钥生成器
            // 生成一个128位的随机源,根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);
            // 3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            // 4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            // 5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            // 6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = Base64.getMimeDecoder().decode(content); // new BASE64Decoder().decodeBuffer();
            /*
             * 解密
             */
            byte[] byte_decode = cipher.doFinal(byte_content);
            return new String(byte_decode, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            log.error("解密出错", e);
        }
        // 如果有错就返加null
        return null;
    }


    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(decryptKey.getBytes());
        keyGenerator.init(128, secureRandom);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * base 64 加密
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.getMimeEncoder().encodeToString(bytes);
    }

    /**
     * base 64 解密
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     */
    public static byte[] base64Decode(String base64Code) {
        return StringUtils.isEmpty(base64Code) ? null : Base64.getMimeDecoder().decode(base64Code);
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    public static void main(String[] args) throws Exception {
        String keyword = "1234";
        String[] password = {
                "Ustc@2024",
                "9Lk>5y<w@dr1",
                "lM8~nO2.tS"
        };
        for (String pass : password) {
            System.out.println("pass：" + pass);
            // String encryptString = AESEncode(pass);
            // System.out.println("AESEncode：" + encryptString);
            // String decryptString = AESDecode(encryptString);
            // System.out.println("AESDecode：" + decryptString);
            System.out.println();
            String baseAESEncode = aesEncrypt(pass, keyword);
            System.out.println("baseAESEncode：" + baseAESEncode);
            String baseAESDecrypt = aesDecrypt(baseAESEncode, keyword);
            System.out.println("baseAESDecrypt：" + baseAESDecrypt);
            System.out.println();
        }
    }

}
