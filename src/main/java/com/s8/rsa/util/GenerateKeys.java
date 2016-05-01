package com.s8.rsa.util;

import com.s8.rsa.constant.S8Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Created by Sourabh_Sethi on 5/1/2016.
 */
public class GenerateKeys {

    /**
     * Helper to provide keypair and saving at location by Client
     * @param Location
     */
    public static void generateKey(String Location) {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(S8Constant.ALGORITHM);
            keyGen.initialize(S8Constant.RSA_BITS);

            final KeyPair key = keyGen.generateKeyPair();

            File privateKeyFile = new File(Location +"/"+S8Constant.privateKeyFileGeneration );
            File publicKeyFile = new File(Location+"/"+S8Constant.publicKeyFileGeneration);

            // Create files to store public and private key
            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();

            // Saving the Public key in a file
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(publicKeyFile));
            publicKeyOS.writeObject(key.getPublic());
            publicKeyOS.close();

            // Saving the Private key in a file
            ObjectOutputStream privateKeyOS = new ObjectOutputStream(
                    new FileOutputStream(privateKeyFile));
            privateKeyOS.writeObject(key.getPrivate());
            privateKeyOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
