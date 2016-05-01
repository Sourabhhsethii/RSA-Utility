package com.s8.rsa.util;

import com.s8.rsa.constant.S8Constant;

import javax.crypto.Cipher;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by Sourabh_Sethi on 4/26/2016.
 */
public class Modulus{

    public static String getModulus( RSAPublicKey rsaPK) {
        String modulus = null;

        try {
            final Cipher cipher = Cipher.getInstance(S8Constant.ALGORITHM);

            modulus = rsaPK.getModulus().toString(16);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modulus;
    }
}
