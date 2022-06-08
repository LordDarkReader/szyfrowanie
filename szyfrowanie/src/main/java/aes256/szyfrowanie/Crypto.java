package aes256.szyfrowanie;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;

public class Crypto {

    private static final String ENCRYPT_ALGO = "AES/CBC/PKCS5Padding";
    private static final String ALGO = "AES";
    private static final String SECRET_KEY_ALGO = "PBKDF2WithHmacSHA256";
    private static final int IV_LENGTH_BYTE = 16;

    static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-ddHH:mm:ss");

    public static IvParameterSpec generateIv() {
        Date date = new Date();
        String format = sdf.format(date);
        return new IvParameterSpec(format.getBytes());
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_ALGO);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), ALGO);
        return secret;
    }


    public static byte[] encryptWithPrefixIV(String pText, SecretKey secret, IvParameterSpec iv) throws Exception {
        byte[] cipherText = encryptAES(pText, secret, iv).getBytes();
        return ByteBuffer.allocate(iv.getIV().length + cipherText.length)
                .put(iv.getIV())
                .put(cipherText)
                .array();
    }

    public static String encryptAES(String password, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(password.getBytes());
        return Base64.encodeBase64String(cipherText);
    }

    public static String decryptWithPrefixIV(byte[] cText, SecretKey secret) throws Exception {

        ByteBuffer bb = ByteBuffer.wrap(cText);
        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);
        return decryptAES(new String(cipherText), secret, new IvParameterSpec(iv));
    }

    public static String decryptAES(String cipherText, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.decodeBase64(cipherText));
        return new String(plainText);
    }
}
