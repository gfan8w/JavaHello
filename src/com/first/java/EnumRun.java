package com.first.java;

import java.io.IOException;

public class EnumRun {

	public static void main(String[] args) throws IOException{
		
		Size size=Size.Big;
		
		String strSize=Size.Small.toString();
		
		size=Size.valueOf(strSize);
		System.out.println(size);
		
		for(Size s :Size.values()){
			System.out.printf("%s:%d ",s,s.ordinal());
		}
		
	}
	
}
