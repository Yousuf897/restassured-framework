package tests;

import config.ConfigLoader;

import java.util.Properties;

public class ConfigTest {

    /*
    * 1: Load the entire properties from the config and print them into the console
    * 2: Load the switch value from the config file
    * 3: store and print the value. (switch),
    * */

    public static void main(String[] args) {

//        System.out.println( ConfigLoader.getProperties() );
//
//        System.out.println( " - - - - - - -  - - - - - - -  - - - - - - -  - - - - - - -  - - - - - - - " );
//
//        Properties properties = ConfigLoader.getProperties();
//        System.out.println( properties );
//
//        System.out.println( " - - - - - - -  - - - - - - -  - - - - - - -  - - - - - - -  - - - - - - - " );
//
//        System.out.println( ConfigLoader.getProperty("test.execute") );
//        String executeTest =  ConfigLoader.getProperty("test.execute");
//        System.out.println( executeTest );

        System.out.println( ConfigLoader.getBooleanProperty("test.execute"));
        System.out.println( ConfigLoader.getIntProperty("timeouts"));


        System.out.println( " - - - - - - -  - - - - - - -  - - - - - - -  - - - - - - -  - - - - - - - " );

    }

}
