package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;


public class Testbase {

    public Properties prop;
    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_500 = 500;
    public int RESPONSE_STATUS_CODE_400 = 400;
    public int RESPONSE_STATUS_CODE_401 = 401;
    public int RESPONSE_STATUS_CODE_201 = 201;

    public Testbase() {
        try {
            prop = new Properties();
            String path = System.getProperty("user.dir");
            FileInputStream ip = new FileInputStream(path + "/src/main/java/com/qa/config/config.properties");
            prop.load(ip);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
