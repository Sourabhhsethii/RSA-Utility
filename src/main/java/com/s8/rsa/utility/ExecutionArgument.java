package com.s8.rsa.utility;

import com.s8.rsa.constant.S8Constant;

/**
 * Created by Sourabh_Sethi on 5/1/2016.
 */
public class ExecutionArgument {
    /**
     * Main Method will responsible  of the Jar Execution.
     *
     * @param args
     */
    public static void main(String args[]) {


        try {

            if (S8Constant.GenerateKeysClient.equals(args[0])) {
                GenerateKeysClient.main(args);
            } else if (S8Constant.ModulusExponentClient.equals(args[0])) {
                ModulusExponentClient.main(args);
            } else if (S8Constant.RsaClient.equals(args[0])) {
                RsaClient.main(args);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("*********************S8*************RSA****************Utility**********************");
            System.out.println("Please pass the Arguments from Below as first argument to utility");
            System.out.println("Example:-");
            System.out.println("java -jar RSA-Encryption-Decryption-1.0.jar Execution Argument");
            System.out.println("1.) GenerateKeysClient - Generates Public Private Keys Pairs");
            System.out.println("2.) ModulusExponentClient - Provide the Modulus and Exponent if Specified public key is there at same location of jar  ");
            System.out.println("3.) RsaClient - Do decryption , if Encrypted String provided to utility along with Private Key");
            System.out.println("*********************S8*************RSA****************Utility**********************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
