package com.clone.semo.common.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JasyptUtil {

    private final String jasyptPassword;
    private final String ALGORITHM = "PBEWithMD5AndDES";

    public JasyptUtil(@Value("${JASYPT_PASSWORD}") String jasyptPassword) {
        this.jasyptPassword = jasyptPassword;
    }

    public String encrypt(String plainText) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(ALGORITHM);
        encryptor.setPassword(jasyptPassword);
        return encryptor.encrypt(plainText);
    }

    public String decrypt(String encryptedText) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(ALGORITHM);
        encryptor.setPassword(jasyptPassword);
        return encryptor.decrypt(encryptedText);
    }
}