package fudan.se.lab2.Assist;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class StringUtils {
    public static String getRandomString(int length,String s) throws NoSuchAlgorithmException {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new SecureRandom();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = rand.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        System.out.println(sb.toString());
        return sb.toString()+s;
    }
}
