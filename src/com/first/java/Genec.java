package com.first.java;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

import javax.naming.PartialResultException;

public class Genec {


	public static void main(String[] args) throws Throwable{
		
		System.out.println(getMin("g","b","c","d","e","f"));
		
		System.out.println(Genec.<Integer>getMin(1,2,3,4,5,6,7,6));
		
		DateInterval dInterval=new DateInterval();
		dInterval.setFirst(new Date(2015,12,11));
		dInterval.setSecond(new Date(2016,3,21));
		
		Pair<Date> pin=dInterval;
		pin.setSecond(new Date(2016,5,13));
		
		
		System.out.println(dInterval.getSecond());
		
		
		new Block() {
			
			@Override
			public void body() throws Exception {
				Scanner inScanner=new Scanner(new File("hh.txt"));
				while(inScanner.hasNext())
					System.out.println(inScanner.next());
				
			}
		}.toThread().start();;
		
		
		Scanner in=new Scanner(System.in);
		in.nextLine();
	}
	
	
	
	
	public static <T> T getMiddle(T...ts){
		return ts[ts.length/2];
		
		
	}
	
	
	public static <T extends Comparable & Serializable> T getMin(T...ts){
		if(ts==null || ts.length==0) return null;
		T small=ts[0];
		for(T t :ts){
			if(small.compareTo(t)>0){
				small=t;
			}
		}
		
		return small;
	}
	
}





/// Serializable 这类接口叫 标签接口，应该放在边界限定条件的最末尾
class Interval<T extends Comparable & Serializable> implements Serializable
{
	public T low;
	public T high;
	
	public Interval(T low, T high)
	{
		if(low.compareTo(high)>0){
			this.high=low;
			this.low=high;
		}
		else{
			this.low=low;
			this.high=high;
		}
	}
}





class Pair<T>
{
	private T first;
	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}

	private T second;
	
	public Pair(){
		first=null;second=null;
		
	}
	
	public Pair(T first, T second){
		this.first=first;
		this.second=second;
	}
	
}



class DateInterval extends Pair<Date>
{
	public void setSecond(Date second) {
		if(second.compareTo(getFirst())>0)
				super.setSecond(second);
	}
}









abstract class Block {
	public abstract void body() throws Exception;
	
	public Thread toThread(){
		return new Thread(){
			 public void run() {
				try{
					body();
				}catch(Throwable t){
					Block.<RuntimeException>throwAs(t);
				}
				 
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Throwable> void throwAs(Throwable e) throws T{
		throw (T)e;
	}
}





















































































