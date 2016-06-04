package com.first.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.Properties;

import javax.swing.text.html.parser.Entity;

public class MyProp {

	public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException{
		
		
			String usrPwd=System.getProperty("user.home");
			String pwd=System.getProperty("user.dir");
			System.out.println(usrPwd+", "+pwd);
		
		
			Properties properties=new Properties();
			properties.setProperty("name", "Jet");
			properties.setProperty("age", "20");
			
			try(FileOutputStream fStream=new FileOutputStream("program.properties"))
			{
				properties.store(fStream, "the program is protected by laws");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				//fStream.close();
			}
			
			
			FileInputStream fStream2 =new FileInputStream("program.properties");
			Properties p=new Properties();
			p.load(fStream2);
			fStream2.close();
			
			for(java.util.Map.Entry<Object, Object> entry: p.entrySet()){
				System.out.println(entry.getKey().toString()+" : "+entry.getValue().toString());
			}
			
			try{
				
			}catch(Exception ex){
				
			}finally {
				
			}
			
			
			
			
			
	}
	
	
	
}
