package utils;

public class DataGenerator {


    public static String generateRandomEmail() {
        return "test" + System.currentTimeMillis() + "@" + ConfigUtility.getEmailDomain();
    }

    public static String generateRandomUserName() {
        return "user" + System.currentTimeMillis();
    }

    public static String generateRandomPassword() {
        return "User@" + System.currentTimeMillis() + "$!";
    }

    public static void main(String[] args) {
        System.out.println( generateRandomUserName() + generateRandomPassword() );
    }
}
