package com.first.java;

import java.lang.Thread.State;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Scanner;

public class MultiThread {


	public static void main(String[] args) throws Throwable{
	
		final int k=0;
		
			Thread thread=new Thread(new Runnable() {
				
				@Override
				public void run()  {
					
					try{
					while(1==1){
							int j=9;
							//k=j;
							j++;
							Thread.sleep(9);
						}
					}
					 catch(Exception ex){
						 System.out.println("异常来自："+Thread.currentThread().getName());
						ex.printStackTrace();
						
					}
					finally {
						
					}
				}
			});
			
			
			
			
			thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.out.println("未捕获的异常:");
					e.printStackTrace();
					
				}
			});
			thread.start();
			
			
			Thread thread2=new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						while(1==1){
							State state=thread.getState();
							System.out.println("thread 1 state:"+state.toString());
							Thread.sleep(1000);
						}
					}catch(Exception ex){
						System.out.println("Exception from thread 2 ");
						ex.printStackTrace();
					}
					
				}
			});
			thread2.start();
			
			
			
			
			Scanner in=new Scanner(System.in);
			System.out.println("press any key to interrupt the thread");
			in.nextLine();
			thread.interrupt();
			
			
			System.out.println("press any key to exit");
			in.nextLine();
			System.exit(0);
		
	}
	
	
	
	
}
