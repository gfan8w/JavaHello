package com.first.company;

public final class Boss extends Manager {

	
	
	public double getSalary(){
		return 100;
	}
	
	
	//final 方法 无法覆盖
	/*@Override
	public String getRegion() {
		return "East China";
	}*/
	
	@Override 
	public int hashCode(){
		return super.hashCode();
	}
	
	
}
