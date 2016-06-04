package com.first.java;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ForkJoinTest {

	public static void main(String[] args) throws Throwable{
		
		final int SIZE=10000000;
		double[] numbers=new double[SIZE];
		for(int i=0;i<SIZE;i++){
			numbers[i]=Math.random();
		}
		
		Counter counter=new Counter(0, numbers.length, new Filter() {
			
			@Override
			public boolean accept(double t) {
				// TODO Auto-generated method stub
				return t>0.5;
			}
		}, numbers);
		
		ForkJoinPool pool=new ForkJoinPool();
		pool.invoke(counter);
		System.out.println(counter.join());
		

		
	}
	
	
	
}



interface Filter{
	boolean accept(double t);
}


class Counter extends RecursiveTask<Integer>{

	private static final int THRESHOLD=1000;
	private int from;
	private int to;
	private Filter filter;
	private double[] values;
	
	public Counter(int from, int to, Filter filter, double[] val){
		this.from=from;
		this.to=to;
		this.filter=filter;
		values=val;
	}
	
	
	@Override
	protected Integer compute() {
		
		if(to-from<THRESHOLD){
			int c=0;
			for(int i=from;i<to;i++){
				if(filter.accept(values[i])){
					c++;
				}
			}
			return c;
		}
		else{
			int mid=(from+to)/2;
			Counter first=new Counter(mid, to, filter, values);
			Counter second=new Counter(from, mid, filter, values);
			invokeAll(first,second);
			return first.join()+second.join();
		}
		
	}
	
	
	
	
}




























