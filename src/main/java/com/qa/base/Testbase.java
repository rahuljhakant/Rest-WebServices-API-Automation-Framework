package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;


public class Testbase {
	
	
	public Properties prop;

	public	Testbase(){
		try {
			prop=new Properties();
			FileInputStream ip = new FileInputStream("/Users/rahul.kant/eclipse-workspace/restapi/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
}
