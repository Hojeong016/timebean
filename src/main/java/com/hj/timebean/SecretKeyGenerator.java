package com.hj.timebean;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static String generateSecretKey(int length) {
        SecureRandom secureRandom;

        try {
            secureRandom = SecureRandom.getInstanceStrong(); // 암호학적으로 안전한 난수 생성기
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Strong secure random algorithm not available, falling back to default SecureRandom.");
            secureRandom = new SecureRandom();
        }

        byte[] randomByte = new byte[length];
        secureRandom.nextBytes(randomByte);
        return Base64.getEncoder().encodeToString(randomByte);

    }

        public static void main (String[]args){
            int keyLength = 35;
            String secretKey = generateSecretKey(keyLength);
            System.out.println(secretKey);

    }
}
