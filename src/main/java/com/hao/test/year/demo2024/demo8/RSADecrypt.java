package com.hao.test.year.demo2024.demo8;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 加密算法RSA解密，druid解密
 *
 * @author xu.liang
 * @since 2024/8/23 10:42
 */
public class RSADecrypt {

    // 从Base64字符串加载公钥
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    // 使用公钥解密
    public static String decrypt(String encryptedText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // 你的公钥字符串（Base64编码）
            String publicKeyStr = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALLLkw5NgHX8TXYBKPlcJHw2Oh3iWjEKJw6nmc0sHXMbYbK4+pzygXKhghl6xAYETJeDRpVvDqnrIyz9w3hJPq8CAwEAAQ==";
            PublicKey publicKey = loadPublicKey(publicKeyStr);

            // 被加密的文本（Base64编码）
            String encryptedText = "b7U2d+h+r+3eLPM1xzumSCthz8Qu50PMS1DCzAnOmCurLfysfDUThtfel6BAoEuv150623eF6VFoQRaECK3h5g==";

            // 解密
            String decryptedText = decrypt(encryptedText, publicKey);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
