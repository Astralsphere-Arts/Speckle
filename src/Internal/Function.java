package Internal;

import java.security.SecureRandom;

/**
 *
 * @author Astralsphere Arts
 */

public class Function {
    static String Seed = "0123456789";
    static SecureRandom random = new SecureRandom();
    
    public static String randomID(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i=0; i<length; i++)
            builder.append(Seed.charAt(random.nextInt(Seed.length())));
        return builder.toString();
    }
}
