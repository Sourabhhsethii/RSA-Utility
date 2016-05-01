package com.s8.rsa.Service;

/**
 * Created by Sourabh_Sethi on 4/26/2016.
 */
public interface DecryptionService {

    public abstract  String decryption(String encryptedTxt);

    public abstract String decryptionSetup(String encryptedTxt , String privateKeyLocation);
}
