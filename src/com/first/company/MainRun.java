package com.first.company;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.out;

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
			
			
			out.println(boss.getAge());
			
			
			ArrayList<Employee> employees=new ArrayList<Employee>(30);
			for(Employee e :managers){
				employees.add(e);
				
			}
			
			for(Employee e :employees){
				if(e!=null)
					out.printf("%s, ", e.getName());
				
					
			}
			
			ArrayList<Integer> aa=new ArrayList<>();
			
			aa.add(Integer.valueOf(3));
			out.println(aa.get(0).intValue());
			
			
			try{
			int ij=Integer.parseInt("3sd");
			}catch(Exception ex)
			{
				out.println(ex.getMessage());
				out.println(ex.getStackTrace());
				ex.printStackTrace();
				out.println();
			}
			
			
			manager.setAge(15);
			manager.setBonus(Double.valueOf(3.4));
			
			Class<? extends Manager> cl=manager.getClass();
		    out.println(cl.getName()+", official name:"+cl.getCanonicalName()+", simple name:"+cl.getSimpleName());
			String className=cl.getName();
			try{
				Class cl2=Class.forName(className+"1");
				manager=(Manager) cl2.newInstance();
			}catch(Exception ex){
				ex.printStackTrace();
				out.println();
			}
		    
		    
		    for(Method method :cl.getMethods())
		    	out.println(Modifier.toString(method.getModifiers())+" "+method.getReturnType().getSimpleName()+" " +method.getName());
		    
		    
		    Constructor<?>[] constructor=cl.getDeclaredConstructors();
		    for(Constructor<?> c :constructor)
		    {
		    	
		    	out.println(Modifier.toString(c.getModifiers())+" "+  c.getName());
		    }
		    
		    
		    
			out.println(double[].class.getName());
			out.println(Double[].class.getName());
			
			
			
			
			
			
			
			
			int[] inta=new int[]{1,2,3,4,5};
			int[] newinta=(int[])goodCopyOf(inta,10);
			
			out.println(Arrays.toString(newinta));
			
			//数组的clone
			int[] cloneInta=inta.clone();
			
			
			
			
			
			
			
			
			
		
	}
	
	
	///无法转换为 Employee[] 类型数组
	public static Object[] badCopyOf(Object[] a, int length){
		Object[] newArray=new Object[length];
		System.arraycopy(a, 0, newArray,0,Math.min(a.length, newArray.length));
		return newArray ;
	}
	
	
	
	public static Object goodCopyOf(Object a, int length) {
		Class class1=a.getClass();
		if(class1.isArray()==false) return null;
		
		Class clCompt=class1.getComponentType();
		int len=Array.getLength(a);
		Object newArray=Array.newInstance(clCompt, len);
		System.arraycopy(a, 0, newArray, 0, Math.min(len, length));
		return newArray;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
