package com.first.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

import javax.lang.model.element.VariableElement;

public class Tiger {
	
	public static void main(String\u005b\u005d args) throws IOException{
		
		
		System.out.println("current dir:"+System.getProperty("user.dir"));
		
		
		
		
		String a=new String("hello");
		System.out.println(a);
		System.out.println(2-1.1);
		
		int ah=0;
		System.out.println(ah);
		
		
		System.out.println(a.equalsIgnoreCase("Hello"));
		StringBuilder sb=new StringBuilder();
		sb.append(4.34);
		sb.append('a');
		System.out.println(sb.toString());
		
		
		
		StringBuffer sbf=new StringBuffer(30);
		sbf.append(sb.toString());
		sbf.toString();
		
		String name="";
		int age=1;
		
		Scanner in =new Scanner(System.in);
		System.out.println("What is your mane:");
		 name=in.nextLine();
		
		System.out.println("How old are you ?");
		 age=in.nextInt();
		
		System.out.printf("your name is %s, age is %d, now is %tF %<tT, %3$tF\n",name,age, new Date());
		
		
		
		//String username=System.console().readLine("user name:");
		//char[] password=System.console().readPassword("password:");
		//System.out.println("user name:"+username+", password:"+password.toString());
		
		
		
		Scanner scannerFile=new Scanner(Paths.get(".\\debug.bat"));
		
		PrintWriter pWriter=new PrintWriter(".\\screen.txt");
		while(scannerFile.hasNextLine()){
			String l=scannerFile.nextLine();
			System.out.println(l);
			pWriter.println(l);
		}
		
		pWriter.close();
		
		
		
		
		
		
		
		
	}
	
}
