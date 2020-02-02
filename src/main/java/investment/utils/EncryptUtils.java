package investment.utils;

import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import static investment.config.ConfigLoader.keyStore;

public class EncryptUtils {
    private static final StandardPBEStringEncryptor ENCRYPTOR = new StandardPBEStringEncryptor();

    static {
        ENCRYPTOR.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
        ENCRYPTOR.setPassword(keyStore());
    }

    public static String encrypt(String text) {
        return ENCRYPTOR.encrypt(text);
    }

    public static String decrypt(String text) {
        return ENCRYPTOR.decrypt(text);
    }

//    public static void main(String[] args) {
//        var mysqlPassword = "adsfasd";
//        var mysqlPasswordEncrypted = encrypt(mysqlPassword);
//        System.out.println(mysqlPasswordEncrypted);
//        System.out.println(decrypt( mysqlPasswordEncrypted));
//    }
}
