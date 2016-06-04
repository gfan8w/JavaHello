package com.first.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import javax.script.ScriptException;
import javax.swing.JTextArea;

public class BigIntegerTest {

	public static void main(String[] args) throws Throwable{
		
		
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				// TODO Auto-generated method stub
				ByteArrayOutputStream b=new ByteArrayOutputStream();
				System.out.println("Cauught by default handler:");
				e.printStackTrace();
				
			}
		});
		
		//ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
		
		boolean isok=true;
		Scanner console=new Scanner(System.in);
		System.out.printf("How many numbers do you need to draw? %n");
		double k=0;
		double n=0;
		try{
		 k=console.nextInt();
		 
		 assert k==0:k;
		 
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
			Logger logger=Logger.getLogger("BigInterTest");
			logger.setLevel(Level.ALL);
			logger.addHandler(new FileHandler("log.txt")); // %h 用户主目录
			logger.addHandler(new WindowsHandler());
			logger.setUseParentHandlers(false);
			logger.log(Level.SEVERE,"错误:"+ex.getMessage(),ex);
			isok=false;
		}
		if(isok){
			System.out.printf("What is the hightest number you can draw: %n");
			
			try{
				n=console.nextInt();
			}catch(Exception ex){
				System.out.println(ex);
				
				Throwable ne=new ScriptException("new Exception got:"+ex.getMessage());
				ne.initCause(ex);
				
				isok=false;
				
				throw ne;
				
				
			}
		
		}
		
		
		if(isok){
			BigInteger lotteryOdds=BigInteger.valueOf(1);
			for(int i=1;i<=k;i++){
				lotteryOdds=lotteryOdds.multiply(BigInteger.valueOf((long)(n-i+1))).divide(BigInteger.valueOf(i));
			}
			
			System.out.println("Your odds are 1 in "+lotteryOdds+". Good Luck!");
		}
		
		System.out.println("dump stack");
		go();
		
		int[] intArr={4,3,8,9,3,2,4,9,1};
		long[] lngArr=new long[]{2,3,4,5,6,7};
		
		Arrays.sort(intArr);
		System.out.println(Arrays.toString(Arrays.copyOf(intArr, intArr.length+2)));
		
		throw new Exception("just my code exception");
		
	}
	
	public static void go(){
		Thread.dumpStack();
	}
	
	
}



class WindowsHandler extends StreamHandler{
	
	public WindowsHandler(){
		final JTextArea out=new JTextArea();
		setOutputStream(new OutputStream() {
			
			@Override
			public void write(int b) {}
			
			@Override
			public void write(byte[] b, int off, int len){
				out.append(new String(b, off, len));
			}
		});
		
	}
	
	
	public void publish(LogRecord log){
		super.publish(log);
		flush();
	}
	
	
	
}










