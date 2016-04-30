package com.s8.encrypt.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import com.s8.encrypt.constant.S8Constant;
import com.s8.encrypt.util.Exponent;
import com.s8.encrypt.util.Modulus;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

/**
 * Created by Sourabh_Sethi on 4/26/2016.
 */
public class RsaClient {
    /**
     * Encrypt the plain text using public key.
     *
     * @param text : original plain text
     * @param :The public key
     * @return Encrypted text
     * @throws java.lang.Exception
     */
    public static byte[] encrypt(String text, RSAPublicKey rsaPK) {
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(S8Constant.ALGORITHM);

            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, rsaPK);

            cipherText = cipher.doFinal(text.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * Decrypt text using private key.
     *
     * @param text :encrypted text
     * @param key  :The private key
     * @return plain text
     * @throws java.lang.Exception
     */
    public static String decrypt(byte[] text, PrivateKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] dectyptedText = null;

        // get an RSA cipher object and print the provider
        final Cipher cipher = Cipher.getInstance(S8Constant.ALGORITHM);

        // decrypt the text using the private key
        cipher.init(Cipher.DECRYPT_MODE, key);
        dectyptedText = cipher.doFinal(text);

        System.out.print("dectyptedText" + dectyptedText);

        return new String(dectyptedText);
    }


    public static byte[] base64ToHex(String base64Value) {
        byte[] decodedBase64 = Base64.decode(base64Value);
        Hex hex = new Hex();
        return hex.encode(decodedBase64);
    }


    public static void generateKey() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(S8Constant.ALGORITHM);
            keyGen.initialize(1024);

            final KeyPair key = keyGen.generateKeyPair();

            File privateKeyFile = new File(S8Constant.PRIVATE_KEY_FILE);
            File publicKeyFile = new File(S8Constant.PUBLIC_KEY_FILE);

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

    /**
     * Test the EncryptionUtil
     */
    public static void main(String[] args) {

        try {

//			generateKey();


            final String originalText = "admin1";
            ObjectInputStream inputStream = null;

            Exponent exponent = new Exponent();
            Modulus modulus = new Modulus();

            String encrypedTxt = "0139b8418b8592025c5c973cae3b211824b089ebfa2cf7f022f7ec5354b3431a3b479a3bb30badae37737d93c3dd3a0d64ee37eaac17643edbb556c42eeb2806f33ed7e34cc0da059de6994858ddf3e00c6f50efafe37b8ca18614e07423256c32e1ceee9b668aa3835b3ca6c35710842e8e131fb838fc2f646bf3cdecc89097";
            String ecrpt =       "0673ac1b486e3e9c431aa4db1b2358ff5350cf7c76acc29b4e3497e1df480b4a4c18d70ee9d0fc97eb1651a893756da5b65c5b00467055324a4921b605cd2af4892b121a2ce2609fa678c32ced3092e2aefd150f20ae182bd7d050eabe55fca129afc4645be2f0fa80e44a3e4e0dda3436f6a919895b59b0824d118342f05b91";
                    //args[0];

            final String cipherText = encrypedTxt;

            // Decrypt the cipher text using the private key.
            inputStream = new ObjectInputStream(new FileInputStream(S8Constant.PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            System.out.println(privateKey.toString());
            System.out.println(new BigInteger(cipherText, 16));



            inputStream = new ObjectInputStream(new FileInputStream(S8Constant.PUBLIC_KEY_FILE));
            final RSAPublicKey publicKey = (RSAPublicKey) inputStream.readObject();
            final String exp = exponent.getExponent(publicKey);
            final String mod = modulus.getModulus(publicKey);

            System.out.println("====Start====Encrypt=====RSA=====Module======");

            System.out.println("Exponent:- " + exp);
            System.out.println("Modulus:-  " + mod);
            System.out.println("Public Key:-  " + publicKey.toString());

            System.out.println("====End======Encrypt=====RSA=====Module======");

            byte[] encryptdeOrignalText = encrypt(originalText,publicKey);

            System.out.println("encryptdeOrignalText:-"+encryptdeOrignalText.toString());

            final String plainText1 = decrypt(encryptdeOrignalText, privateKey);


            System.out.println("dencryptdeOrignalText:-"+plainText1);

            final String plainText = decrypt(DatatypeConverter.parseHexBinary(cipherText), privateKey);


            System.out.println("Original Text: " + originalText);
            System.out.println("Encrypted Text: " + cipherText.toString());
            System.out.println("Decrypted Text: " + plainText);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please pass argument 1 as encrypted text to decrypt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
