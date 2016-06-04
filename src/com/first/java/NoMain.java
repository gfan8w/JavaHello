package com.first.java;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.first.java.TalkingClock.TimePrinter;

public class NoMain {

	static{
		System.out.println("hello, no main method");
		//System.exit(0);
	}
	
	public static void main(String[] args) throws IOException{
		TalkingClock tClock=new TalkingClock("flag1");
		
		tClock.start();
		
		
		ActionListener listener=tClock.new TimePrinter();
		Timer timer=new Timer(3000, listener);
		timer.start();
		
		
		
		
		
		
		
		
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
		
	}
	
}

 class TalkingClock
 {
	 private int isPrint=0;
	 
	 
	 public void Run(int interval, final boolean beep){
		 
		 int jump=6;
		 
		 ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date now=new Date();
				if(beep){
					System.out.println(jump+" %2 At the tone, the time is "+now);
					Toolkit.getDefaultToolkit().beep();
				}
				
			}
		};
	 }
	 
	 
	 public void Begin(int interval, final boolean beep){
		 int jump=3;
		 
		 class BookTime  implements ActionListener
			{
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Date now=new Date();
					if(beep){
						System.out.println(jump+" %2 At the tone, the time is "+now);
						Toolkit.getDefaultToolkit().beep();
					}
				}
			}
		 
		 ActionListener listener=new BookTime();
		 Timer timer=new Timer(interval, listener);
		 timer.start();
		 
		 
		 
	 }
	 
	 
	 
	 
	 
	 
	 public void start(){
		 TimePrinter tPrinter=new TimePrinter();
		 Timer timer=new Timer(2000, tPrinter);
		 Begin(1000, true);
		 timer.start();
	 }
	 
	 private String flag="";
	 
	 public TalkingClock(String flag){
		 this.flag=flag;
	 }
	 
	 
	 
	public class TimePrinter implements ActionListener
	{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			Date now=new Date();
			if(TalkingClock.this.isPrint%2==0){
				System.out.println(isPrint+" %2 At the tone, the time is "+now);
				Toolkit.getDefaultToolkit().beep();
				try{
					Thread.sleep(60000);
				}catch(Exception ex){
					System.out.println();
					ex.printStackTrace();
				}
				
			}
			else if(TalkingClock.this.isPrint%3==0){
				System.out.println(isPrint+" %3 At the tone, the time is "+now);
				Toolkit.getDefaultToolkit().beep();
			}
			isPrint++;
		}
	}
}
 
 
 
 
 
 
 class Book
 {
	 public String getName(){
		 
		 return "Travel";
	 }
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 

