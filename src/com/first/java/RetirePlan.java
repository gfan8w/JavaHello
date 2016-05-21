package com.first.java;

import java.io.IOException;
import java.util.Scanner;

public class RetirePlan {
	public static void main(String\u005b\u005d args) throws IOException{
		
		boolean isok=true;
		Scanner console=new Scanner(System.in);
		System.out.printf("How much money will you contribute every year? %n");
		double payment=0;
		double interestRate=0;
		try{
		 payment=console.nextDouble();
		}catch(Exception ex){
			System.out.println(ex);
			isok=false;
		}
		if(isok){
			System.out.printf("Interest reate in %%: %n");
			
			try{
				interestRate=console.nextDouble();
			}catch(Exception ex){
				System.out.println(ex);
				isok=false;
			}
			
			double balance=0;
			int year=0;
			
			String input="";
			do{
				balance+=payment;
				double interest=balance* interestRate/100;
				balance+=interest;
				
				year++;
				
				System.out.printf("After year %d, your balance is %,.2f%n", year,balance);
				
				System.out.println("Do you want to retire? £¨Y/N£©£º");
				input=console.next();
				System.out.println("input is "+input);
			}while(input.equalsIgnoreCase("N"));
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	
	
}
