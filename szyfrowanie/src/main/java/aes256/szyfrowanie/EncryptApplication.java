package aes256.szyfrowanie;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.print.Pageable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class EncryptApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EncryptApplication.class, args);


        //password              salt        expirationTimeFrom       expirationTimeTO
        //25405651242518096863  4902326256  2022-02-01 01:00:00.000  2022-02-28 23:59:59.00

        String plainTextToEncrypt = "plk123456778";

        SecretKey secretKey = Crypto.getKeyFromPassword("25405651242518096863" , "4902326256");

        IvParameterSpec ivParameterSpec = Crypto.generateIv();

        //Szyfrowanie po stronie Nellson
        byte[] cipherTextByte = Crypto.encryptWithPrefixIV(plainTextToEncrypt, secretKey, ivParameterSpec);

        //Deszyfrowanie po stronie BRP
        String decryptAESString = Crypto.decryptWithPrefixIV(cipherTextByte, secretKey);

//        String dd = "22-01-0413:15:56gZI4UZfMahTDXcyOzqUiN2yA8Et8Htizg0bf00qcCY4=";
//        byte[] b = dd.getBytes();
//        String decryptAESString2 = Crypto.decryptWithPrefixIV(b, secretKey);

        //22-01-0512:04:343WvJjy4L7THGudvzEe3Mgg==
        //22-01-0512:05:22wo27N6eg6ue43iYoHbrDbg==

        //22-05-1714:29:27FQ8B87EwEpVo0W4By19lpfJNvQpiVVeHJluV+xXLRKE=
        //22-02-1715:23:53tmNBywpiSAvBJy+giV9tWg==
        //22-02-1715:24:09xY1wz5KbSvb+aiDbr7ab9g==

//        System.out.println("Plain text: " + plainTextToEncrypt);
//        System.out.println("Cipher text: " + new String(cipherTextByte));
//        System.out.println("Decrypt text: " + decryptAESString);
//        //System.out.println("Decrypt text2: " + decryptAESString2);
//        WriteFile writeFile = new WriteFile();
//        for (int i = 0; i < 4; i++) {
//
//            writeFile.saveNotifyPartnerProductStateToFile(String.valueOf(i));
//        }
//
//
//        WriteFile writeFile2 = new WriteFile();
//        List<String> read = writeFile2.read();
//        for (String info : read) {
//            System.out.println("info: " +  info);
//            String[] split = info.split("/");
//            System.out.println("split[0]: " +  split[0]);
//            System.out.println("split[1]: " +  split[1]);
//        }




    }

}
