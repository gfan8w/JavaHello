package com.first.java;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class BigIntegerTest {

	public static void main(String[] args) throws IOException{
		
		boolean isok=true;
		Scanner console=new Scanner(System.in);
		System.out.printf("How many numbers do you need to draw? %n");
		double k=0;
		double n=0;
		try{
		 k=console.nextInt();
		}catch(Exception ex){
			System.out.println(ex);
			isok=false;
		}
		if(isok){
			System.out.printf("What is the hightest number you can draw: %n");
			
			try{
				n=console.nextInt();
			}catch(Exception ex){
				System.out.println(ex);
				isok=false;
			}
		
		}
		
		
		if(isok){
			BigInteger lotteryOdds=BigInteger.valueOf(1);
			for(int i=1;i<=k;i++){
				lotteryOdds=lotteryOdds.multiply(BigInteger.valueOf((long)(n-i+1))).divide(BigInteger.valueOf(i));
			}
			
			System.out.println("Your odds are 1 in "+lotteryOdds+". Good Luck!");
		}
		
		int[] intArr={4,3,8,9,3,2,4,9,1};
		long[] lngArr=new long[]{2,3,4,5,6,7};
		
		Arrays.sort(intArr);
		System.out.println(Arrays.toString(Arrays.copyOf(intArr, intArr.length+2)));
		
		
		
	}
	
	
	
}
