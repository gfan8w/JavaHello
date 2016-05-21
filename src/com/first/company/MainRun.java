package com.first.company;

import java.io.IOException;

public class MainRun {

	public static void main(String[] args) throws IOException{
	
			Manager manager =new Manager();
			Manager[] managers=new Manager[3];
			managers[0]=manager;
			managers[0].getAge();
			
			
			
			Employee boss=manager;
			
			if(boss instanceof Manager){
				
				((Manager) boss).getRegion();
			}
			
			
			boss.getAge();
			
		
	}
}
