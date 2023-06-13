package com.hao.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

/**
 * @author xu.liang
 * @since 2022/10/11 09:08
 */
public class AESUtil {
    /**
     * 加密算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 字符集
     */
    private static final String CHAR_SET = "UTF-8";

    /**
     * AES的密钥长度
     */
    private static final Integer SECRET_KEY_LENGTH = 128;

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/GCM/NoPadding";

    /**
     * 默认加密密钥
     */
    private static final String DEFAULT_KEY = " 4BB90812C2B9B0882A6FA7C203E4717F";

    /**
     * private禁止实例化
     **/
    private AESUtil() {
    }

    /**
     * AES加密操作
     *
     * @param content  待加密内容
     * @param password 加密秘钥
     * @return 返回Base64转码后的加密内容
     * @throws Exception 异常
     */
    public static String encrypt(String content, String password) throws Exception {
        byte[] byteContent = content.getBytes(CHAR_SET);
        byte[] encryptBytes = edecrypt(byteContent, password, Cipher.ENCRYPT_MODE);
        return Base64.encodeBase64String(encryptBytes);
    }

    /**
     * AES解密操作
     *
     * @param content  加密的密文
     * @param password 解密密钥
     * @return 解密后内容
     * @throws Exception 异常
     */
    public static String decrypt(String content, String password) throws Exception {
        byte[] byteContent = Base64.decodeBase64(content);
        byte[] decryptBytes = edecrypt(byteContent, password, Cipher.DECRYPT_MODE);
        return new String(decryptBytes, CHAR_SET);
    }

    /**
     * AES加密操作
     *
     * @param content 待加密内容
     * @return 加密后内容
     * @throws Exception 异常
     */
    public static String encrypt(String content) throws Exception {
        return encrypt(content, DEFAULT_KEY);
    }

    /**
     * AES解密操作
     *
     * @param content 加密的密文
     * @return 解密后内容
     * @throws Exception 异常
     */
    public static String decrypt(String content) throws Exception {
        return decrypt(content, DEFAULT_KEY);
    }

    private static byte[] edecrypt(byte[] content, String password, int mode) throws Exception {
        if (StringUtils.isEmpty(password) || null == content || content.length == 0) {
            throw new Exception("内容或秘钥不能为空！");
        }
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        SecretKeySpec secretKey = getSecretKey(password);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(SECRET_KEY_LENGTH, password.getBytes());
        cipher.init(mode, secretKey, gcmParameterSpec);
        return cipher.doFinal(content);
    }

    /**
     * 获取Key
     *
     * @param password 密码
     * @return SecretKeySpec
     * @throws NoSuchAlgorithmException 异常
     */
    private static SecretKeySpec getSecretKey(final String password) throws NoSuchAlgorithmException {
        //防止linux下 随机生成key
        Provider p = Security.getProvider("SUN");
        // 生成指定算法密钥的生成器
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", p);
        secureRandom.setSeed(password.getBytes());
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(SECRET_KEY_LENGTH, secureRandom);
        // 生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 转换成AES的密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }

    public static void main(String[] args) throws Exception {
        String encryptResult = AESUtil.encrypt("testpassowrd");
        System.out.println("AES加密后密码："+encryptResult);
        String decryptResult = AESUtil.decrypt(encryptResult);
        System.out.println("AES解密后密码："+decryptResult);
    }

}
