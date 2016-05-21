package com.first.company;

import java.util.Calendar;
import java.util.Date;

public class Manager extends Employee {
	
	private double bonus;

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
		int a=getAge();
	}
	
	
	@Override
	public double getSalary(){
		return 10;
	}
	
	
	
	public final String getRegion(){
		this.hireDate=Calendar.getInstance().getTime();
		
		
		return "SouthEast";
	}
	
	
	
}
