package in.s8.rsa.controller;

import in.s8.rsa.Service.EncryptionService;
import in.s8.rsa.constant.S8Constant;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by Sourabh_Sethi on 4/26/2016.
 */

@Component("encryptionService")
public class EncrptController implements EncryptionService {

    public byte[] encryptionByteStream(String plainText, RSAPublicKey rsaPK) {
        byte[] cipherText = null;
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(new FileInputStream(S8Constant.PUBLIC_KEY_FILE));

            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(S8Constant.ALGORITHM);

            // rsa the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, rsaPK);

            cipherText = cipher.doFinal(plainText.getBytes());

        } catch (FileNotFoundException e) {

            System.out.println("Please check the public key and Private Key Path");
            System.out.println("Path Should be ./public.key");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    public String encryptionString(String plainText, RSAPublicKey rsaPK) {

        String cipherText = null;
        cipherText = encryptionByteStream(plainText, rsaPK).toString();

        return cipherText;
    }
}
