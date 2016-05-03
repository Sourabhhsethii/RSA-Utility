package in.s8.rsa.utility;

import static in.s8.rsa.util.GenerateKeys.generateKey;

/**
 * Created by Sourabh_Sethi on 5/1/2016.
 */
public class GenerateKeysClient {

    /**
     * Client to Generate the 1024 Bit key pairs.
     * @param args
     */
    public static void main(String args[]) {

        try {
            String keyLoaction = null;
            keyLoaction = args[1];
            generateKey(keyLoaction);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please pass the path as argument 1");
            System.out.println("Example:-");
            System.out.println("java -cp RSA-Encryption-Decryption-1.0.jar GenerateKeysClient D:\\keypairs");
            System.out.println("OR");
            System.out.println("java -jar RSA-Encryption-Decryption-1.0.jar GenerateKeysClient D:\\keypairs");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
