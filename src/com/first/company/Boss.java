package com.first.company;

public final class Boss extends Manager {

	
	
	public double getSalary(){
		return 100;
	}
	
	
	//final ���� �޷�����
	/*@Override
	public String getRegion() {
		return "East China";
	}*/
	
	@Override 
	public int hashCode(){
		return super.hashCode();
	}
	
	
}
