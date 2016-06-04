package com.first.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;

public class BlockingQueueTest {

	public static void main(String[] args) throws Throwable{
		
		Scanner in=new Scanner(System.in);
		System.out.println("Enter base directory, like c:\\use");
		String dir=in.nextLine();
		System.out.println("Enter the keyword, like 'volatile'");
		String keyword=in.nextLine();
		
		final int File_QUEUE_SIZE=10;
		final int SEARCH_THREADS=1;
		BlockingQueue<File> queue=new ArrayBlockingQueue<File>(File_QUEUE_SIZE);
		
		FileEnumerationTask enumerationTask=new FileEnumerationTask(queue, new File(dir));
		new Thread(enumerationTask).start();
		for(int i=0;i<SEARCH_THREADS;i++){
			new Thread(new SearchTask(queue, keyword)).start();
		}
		
		//Task, Futuer
		MatchCounter mCounter=new MatchCounter(new File(dir), keyword);
		
		
		System.out.println(mCounter.call());
		
		
		
		//ThreadPool Ö´ÐÐÆ÷
		ExecutorService pool=Executors.newCachedThreadPool();
		MatchNumberCounter matchNumberCounter=new MatchNumberCounter(new File(dir), keyword, pool);
		Future<Integer> result=pool.submit(matchNumberCounter);
		System.out.println(result.get());
		
		
		
		ScheduledExecutorService schedulePool= Executors.newScheduledThreadPool(6);
		
		
		
		
		//
		ConcurrentHashMap<String, Employee> concurrentHashMapEmp=new ConcurrentHashMap<String, Employee>(10);
		
		concurrentHashMapEmp.putIfAbsent("123-09-98", new Employee("Jet ma"));
		
		CopyOnWriteArraySet<Employee> copyOnWriteArraySetEmp=new CopyOnWriteArraySet<>();
		copyOnWriteArraySetEmp.add(new Employee("Bruce Li"));
		
		
		
		
		
		
		
		
		
		System.out.println("press any key to exit");
		in.nextLine();
		System.exit(0);
		
		
	}
	
	
	
	
	
	
}



class FileEnumerationTask implements  Runnable{
	
	public static File DUMMY=new File("");
	
	private BlockingQueue<File> queue;
	private File startingDirectory;
	
	
	public FileEnumerationTask(BlockingQueue<File> q, File startDir){
		this.queue=q;
		this.startingDirectory=startDir;
	}
	
	
	
	public void run() {
		try{
			enumerate(startingDirectory);
			enumerate(DUMMY);
		}
		catch(Exception ex){
			
		}
		
	}
	
	
	protected void enumerate(File directory) throws InterruptedException {
		
		File[] files=directory.listFiles();
		for(File file :files){
			if(file.isDirectory()){
				enumerate(file);
			}
			else{
				queue.put(file);
			}
		}
		
	}
	
}


class SearchTask implements  Runnable {
	
	private BlockingQueue<File> queue;
	private String keyword;
	
	
	public SearchTask(BlockingQueue<File> q, String keyword){
		this.queue=q;
		this.keyword=keyword;
	}
	
	
	
	public void run() {
		
		try{
			boolean isDone=false;
			while(!isDone){
				File file=queue.take();
				if(file==FileEnumerationTask.DUMMY){
					queue.put(FileEnumerationTask.DUMMY);
					isDone=true;
				}
				else{
					search(file);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		
	}
	
	
	private void search(File file) throws IOException{
		
		try(Scanner in=new Scanner(new FileInputStream(file),"UTF-8")){
			int lineNumber=0;
			while (in.hasNextLine()) {
				lineNumber++;
				String line=in.nextLine();
				if(line.contains(keyword)){
					System.out.printf("%s:%d:%s%n", file.getPath(),lineNumber,line);
				}
				
			}
		}
		
	}
	
	
}



class MatchCounter implements Callable<Integer>{
	
	private File directory;
	private String keywork;
	private int count;
	
	public  MatchCounter(File dir, String keyword) {
		// TODO Auto-generated constructor stub
		this.directory=dir;
		this.keywork=keyword;
	}
	
	
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		count=0;
		try{
			
			File[] files=directory.listFiles();
			List<Future<Integer>> results=new ArrayList<Future<Integer>>();
			
			
			for(File file :files)
			{
				if(file.isDirectory()){
					MatchCounter mCounter=new MatchCounter(file, keywork);
					FutureTask<Integer> ftask=new FutureTask<Integer>(mCounter);
					results.add(ftask);
					
					new Thread(ftask).start();
				}else{
					count+=search(file);
				}	
			}
			
			
			for(Future<Integer> f: results){
				try{
					count+=f.get();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			
			
		}catch(Exception ex){
			
			
			
		}
		return count;
	}
	
	
	
	private Integer search(File file) throws FileNotFoundException{
		Scanner fScanner=new Scanner(new FileInputStream(file),"UTF-8");
		Integer cInteger=0;
		while(fScanner.hasNextLine()){
			if(fScanner.nextLine().contains(keywork)){
				cInteger++;
			}
		}
		return cInteger;
	}
	
}





class MatchNumberCounter implements Callable<Integer>{
	
	private File directory;
	private String keywork;
	private int count;
	
	private ExecutorService pool;
	
	
	
	public  MatchNumberCounter(File dir, String keyword, ExecutorService pool) {
		// TODO Auto-generated constructor stub
		this.directory=dir;
		this.keywork=keyword;
		this.pool=pool;
	}
	
	
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		count=0;
		try{
			
			File[] files=directory.listFiles();
			List<Future<Integer>> results=new ArrayList<Future<Integer>>();
			
			
			for(File file :files)
			{
				if(file.isDirectory()){
					MatchNumberCounter mCounter=new MatchNumberCounter(file, keywork,pool);
					
					Future<Integer> ftask= pool.submit(mCounter);
					
					FutureTask<Integer> ftask2=new FutureTask<Integer>(mCounter);
					results.add(ftask);
					
					//new Thread(ftask).start();
					
					
					
				}else{
					count+=search(file);
				}	
			}
			
			
			for(Future<Integer> f: results){
				try{
					count+=f.get();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			
			
		}catch(Exception ex){
			
			
			
		}
		return count;
	}
	
	
	
	private Integer search(File file) throws FileNotFoundException{
		Scanner fScanner=new Scanner(new FileInputStream(file),"UTF-8");
		Integer cInteger=0;
		while(fScanner.hasNextLine()){
			if(fScanner.nextLine().contains(keywork)){
				cInteger++;
			}
		}
		return cInteger;
	}
	
}

















































































