package utils;

public class RandomEmailGenerator {


    public static String generateRandomEmail() {
        return "test" + System.currentTimeMillis() + "@" + ConfigUtility.getEmailDomain();
    }

    public static void main(String[] args) {
        System.out.println( generateRandomEmail() );
    }
}
