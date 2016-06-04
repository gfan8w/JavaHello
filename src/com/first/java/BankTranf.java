package com.first.java;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.*;

public class BankTranf {

	private static final int NACCOUNT = 3;
	public static final double INITAL_BALANCE = 1000;

	public static final ThreadLocal<SimpleDateFormat> dateFormat=new ThreadLocal<SimpleDateFormat>(){
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	
	public static void main(String[] args) {

		Bank bank = new Bank(NACCOUNT, INITAL_BALANCE);

		for (int i = 0; i < NACCOUNT; i++) {

			TransferRunnable transferRunnable = new TransferRunnable(bank, i, INITAL_BALANCE);

			Thread thread = new Thread(transferRunnable);
			thread.start();
			
			final int j=i;
			
			Thread thread2=new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						Thread.sleep((int)(3*Math.random()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Calendar calendar=Calendar.getInstance();
					calendar.set(2015, Calendar.JANUARY, (j+3));
					System.out.println(dateFormat.get().format(calendar.getTime()));
					
					ThreadLocalRandom.current().nextInt();
					
				}
			});
			
			
		    thread2.start();
			
			
			
			
			
		}

		System.out.println("press any key to exit.");
		Scanner in = new Scanner(System.in);
		in.nextLine();
		System.exit(0);
	}

}

class Bank {
	private final double[] account;
	private Lock bankLock;
	private Condition sufficientFund;

	public Bank(int n, double initialBanlance) {
		account = new double[n];
		for (int i = 0; i < account.length; i++) {
			account[i] = initialBanlance;
		}

		bankLock = new ReentrantLock();
		sufficientFund = bankLock.newCondition();

	}

	public /*synchronized*/ void transfer(int from, int to, double amount) {
		bankLock.lock();

		try {
			while (account[from] < amount) {
				System.out.println(Thread.currentThread().getName() + " does'nt hava enough money");
				sufficientFund.await();
				//wait();
			}

			System.out.println(Thread.currentThread());

			account[from] -= amount;
			System.out.printf("%10.2f from %d to %d ", amount, from, to);
			account[to] += amount;
			System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
			sufficientFund.signalAll();
			//notifyAll();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
	}

	public /*synchronized*/ double getTotalBalance() {
		double d = 0;
		
		try {
			for (double n : account) {
				d += n;
			}
			return d;
		} finally {
			
		}
	}

	public int size() {
		return account.length;
	}

}

class TransferRunnable implements Runnable {

	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;

	public TransferRunnable(Bank b, int from, double max) {
		bank = b;
		this.fromAccount = from;
		this.maxAmount = max;
	}

	@Override
	public void run() {

		try {
			while (true) {
				int toAcount = (int) (bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAcount, amount);
				Thread.sleep(4000/* (int)(DELAY*Math.random()) */);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
