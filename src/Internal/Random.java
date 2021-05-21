package Internal;

import java.security.SecureRandom;

/**
 *
 * @author Astralsphere Arts
 */

public class Random {
    static String Seed = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom random = new SecureRandom();
    
    public static String ID(int length){
        StringBuilder builder = new StringBuilder(length);
        for (int i=0; i<length; i++)
            builder.append(Seed.charAt(random.nextInt(Seed.length())));
        return builder.toString();
    }
}
