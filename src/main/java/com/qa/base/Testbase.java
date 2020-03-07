package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;


public class Testbase {

	public Properties prop;

	public	Testbase(){
		try {
			prop=new Properties();
			String path = System.getProperty("user.dir");
			FileInputStream ip = new FileInputStream(path+"/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
