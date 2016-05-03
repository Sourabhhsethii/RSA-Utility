package in.s8.rsa.utility;

import in.s8.rsa.constant.S8Constant;
import in.s8.rsa.util.Exponent;
import in.s8.rsa.util.Modulus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import java.security.interfaces.RSAPublicKey;

/**
 * Created by Sourabh_Sethi on 4/26/2016.
 */
public class ModulusExponentClient {

    public static void main(String[] args) {

        Exponent exponent = new Exponent();
        Modulus modulus = new Modulus();

        try {
            ObjectInputStream inputStream = null;

            inputStream = new ObjectInputStream(new FileInputStream(S8Constant.PUBLIC_KEY_FILE));
            final RSAPublicKey publicKey = (RSAPublicKey) inputStream.readObject();
            final String exp = exponent.getExponent(publicKey);
            final String mod = modulus.getModulus(publicKey);

            System.out.println("====Start====Encrypt=====RSA=====Module======");

            System.out.println("Exponent:- " + exp);
            System.out.println("Modulus:-  " + mod);

            System.out.println("====End======Encrypt=====RSA=====Module======");

        } catch (FileNotFoundException e) {

            System.out.println("Please check the public key and Private Key Path");
            System.out.println("Path Should be ./private.key");
            System.out.println("Path Should be ./public.key");

        } catch (Exception e) {
            e.printStackTrace();


        }


    }


}
