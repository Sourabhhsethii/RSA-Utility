package in.s8.rsa.controller;

import in.s8.rsa.Service.DecryptionService;
import in.s8.rsa.constant.S8Constant;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

/**
 * Utility to decrypt the Data.
 * Created by Sourabh_Sethi on 4/26/2016.
 */

@Component("decryptionService")
public class DecryptController implements DecryptionService {


    public String decryption(String encryptedTxt) {

        ObjectInputStream inputStream = null;
         String plainText = null;

        try {

            inputStream = new ObjectInputStream(new FileInputStream(S8Constant.PRIVATE_KEY_FILE));

            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            String cipherText = encryptedTxt;

              plainText = decrypt(DatatypeConverter.parseHexBinary(cipherText), privateKey);
        } catch (FileNotFoundException e) {

            System.out.println("Please check the public key and Private Key Path");
            System.out.println("Path Should be ./private.key");

        } catch (Exception e) {
            e.printStackTrace();

        }

        return plainText;

    }

    public String decryptionSetup(String encryptedTxt , String privateKeyLocation) {

        ObjectInputStream inputStream = null;
        String plainText = null;

        try {

            inputStream = new ObjectInputStream(new FileInputStream(privateKeyLocation));

            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            String cipherText = encryptedTxt;

            plainText = decrypt(DatatypeConverter.parseHexBinary(cipherText), privateKey);
        } catch (FileNotFoundException e) {

            System.out.println("Please check the public key and Private Key Path");
            System.out.println("please provide the private key path");

        } catch (Exception e) {
            e.printStackTrace();

        }

        return plainText;

    }

    /**
     * decrypt the data Using Private Key.
     * @param text
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String decrypt(byte[] text, PrivateKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] dectyptedText = null;

        // get an RSA cipher object and print the provider
        final Cipher cipher = Cipher.getInstance(S8Constant.ALGORITHM);

        // decrypt the text using the private key
        cipher.init(Cipher.DECRYPT_MODE, key);
        dectyptedText = cipher.doFinal(text);

        return new String(dectyptedText);
    }


}
