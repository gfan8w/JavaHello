package com.first.java;

import java.io.IOException;

public class Foor {

	public static void main(String[] args) throws IOException{
		
		
		
		
		
		
		
		
		
		
		
		
		loop_label:
		for(float i=0;i!=10;i+=0.1){
			System.out.println(i);
			
			Size s=Size.Small;
			String strSize="";
			if(i>10){
				s=Size.Mediem;
				strSize="small";
			}
			if(i>20){
				s=Size.Big;
				strSize="big";
			}
			
			switch(s){
				case Small:
					System.out.println("small");
					break;
				default:
					System.out.println("not size");
			}
			
			switch (strSize) {
			case "small":
				System.out.println("small string");
				break;

			default:
				System.out.println("not size string");
				break;
			}
			
			
			
			
			if(i>300000){
				break;
			}
			
			
			
			for(float j=i;j!=100;j+=0.1)
			{
				
				if(j>30){
					break loop_label;// break 语句可以带标签，跳出最外层循环
				}
				
				System.out.println(j);
				
				
			}
			
			
			
			
		}
	
	
	}
	
	
	
	
	
}






 









