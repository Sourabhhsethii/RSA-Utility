package com.s8.encrypt.util;

import com.s8.encrypt.constant.S8Constant;

import javax.crypto.Cipher;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by Sourabh_Sethi on 4/26/2016.
 */
public class Exponent {

    public static String getExponent( RSAPublicKey rsaPK) {
        String exponent = null;

        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(S8Constant.ALGORITHM);

            exponent = rsaPK.getPublicExponent().toString(16);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return exponent;
    }
}
