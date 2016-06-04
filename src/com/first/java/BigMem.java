package com.first.java;

import java.util.ArrayList;
import java.util.Scanner;

public class BigMem {

	public static void main(String[] args) throws Throwable{
	
		Scanner in=new Scanner(System.in);
		
		System.out.println("Press any key to start");
		in.nextLine();
		
		
		ArrayList<Student> stu=new ArrayList<>();
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
					for(int i=0;i<1000000;i++){
						final int j=i;
						stu.add(new Student(){{ setAge(j); setName("my dad name is "+j); }});
						String juString=String.valueOf(j);
						try{
							new Exception("My throw  exception is here.");
						}catch(Exception ex)
						{
							String aString=ex.getClass().getName();
							aString=aString+" good";
							aString+=" is ok";
							aString+=" "+juString;
						}
					
				}
			}
		});
		
		
		thread.start();
		
		
		ArrayList<Student> students=new ArrayList<>();
		for(int i=0;i<1000000;i++){
			final int j=i;
			students.add(new Student(){{ setAge(j); setName("my name is "+j); }});
			String juString=String.valueOf(j);
			try{
				new Exception("My throw  exception is here.");
			}catch(Exception ex)
			{
				String aString=ex.getClass().getName();
				aString=aString+" good";
				aString+=" is ok";
				aString+=" "+juString;
			}
			
			
		}
		
		
		System.out.println("done, press any key to exit");
		in.nextLine();
		
		
	}
	
	
}


class Student
{
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private long age;
	private  String name;
		
	
}