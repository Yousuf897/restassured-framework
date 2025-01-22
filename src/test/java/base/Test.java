package base;

import config.ConfigLoader;

public class Test {

    public static void main(String[] args) {

        System.out.println( ConfigLoader.getProperties() );
        System.out.println( ConfigLoader.getProperty("test.suite") );
        System.out.println( ConfigLoader.getIntProperty("timeouts") );

        int timeouts = ConfigLoader.getIntProperty("timeouts");
        System.out.println( timeouts );
        System.out.println( timeouts + 10 );

    }

}
