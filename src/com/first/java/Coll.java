package com.first.java;


import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

import javax.lang.model.element.Element;
import javax.xml.crypto.dsig.keyinfo.PGPData;

public class Coll {

	public static void main(String[] args) throws Throwable{
		
		Collection<String> liStrings=new ArrayList<String>(){{add("hello");add("world"); add("good"); }};
		//Enumeration<String> enumeration=(Enumeration<String>) liStrings;
		
		
		Iterator<String> iterator=liStrings.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
			iterator.remove();
			
			
		}
		
		System.out.println(liStrings.size());
		
		
		/*while(enumeration.hasMoreElements()){
			System.out.println(enumeration.nextElement());
		}*/
		
		
		LinkedList<String> staffll=new LinkedList<String>();
		List<String> staff=staffll;
		
		
		
		staff.add("my");
		staff.add("god");
		staff.add("my");
		staff.add("My");
		
		
		for(String s : staff){
			System.out.println(s);
		}
		
		
		ListIterator<String> iis=staff.listIterator();
		Vector<Integer> vector=new Vector<Integer>(10);
		vector.add(4);
		vector.addElement(5);
		
		Iterator<Integer>vIterator=vector.iterator();
		
		Set<String> strings=new HashSet<>(staff);
		
		System.out.println();
		Iterator<String> iss= strings.iterator();
		
		while(iss.hasNext()){
			System.out.println(iss.next());
		}
		
		
		TreeSet<String> tSet=new TreeSet<>(new Comparator<String>() {
			public int compare(String a, String b) {
				return b.compareToIgnoreCase(a);
			}
		});
		tSet.add("jim");
		tSet.add("good");
		tSet.add("alice");
		tSet.add("peter");
		tSet.add("ALice");
		
		for(String iString :tSet){
			System.out.println(iString);
		}
		
		
		
		
		Deque<String> dStrings=new ArrayDeque<>(tSet);
		
		dStrings=new LinkedList<>(tSet);
		
		
		
		
		
		PriorityQueue<GregorianCalendar> pQueue=new PriorityQueue<>();
		pQueue.add(new GregorianCalendar(1906,Calendar.DECEMBER,9));
		pQueue.add(new GregorianCalendar(1815,Calendar.DECEMBER,10));
		pQueue.add(new GregorianCalendar(1903,Calendar.DECEMBER,3));
		pQueue.add(new GregorianCalendar(1910,Calendar.JUNE,22));
		
		System.out.println("Iterating over element:");
		for(GregorianCalendar g: pQueue){
			System.out.println(g.get(Calendar.YEAR)); // 迭代的时候不有序
		}
		System.out.println("Removing elements");
		while(pQueue.isEmpty()==false){
			System.out.println(pQueue.remove().get(Calendar.YEAR)); // 删除的时候有序
		}
		
		
		
		Map<String, Employee> eMap=new HashMap<>();
		eMap.put("888-654-125", new Employee("josh bloch"));
		eMap.put("888-654-122", new Employee("Deng Dick"));
		eMap.put("147-654-125", new Employee("Peter Park"));
		
		
		Employee e=eMap.get("147-654-125");
		
		for(Map.Entry<String, Employee> entry:eMap.entrySet()){
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		System.out.println(eMap);
		
		
		
		
		EnumSet<WeekDay> weekDays=EnumSet.allOf(WeekDay.class); //所有集合
		EnumSet<WeekDay> none=EnumSet.noneOf(WeekDay.class);
		EnumSet<WeekDay> workday=EnumSet.of(WeekDay.Monday, WeekDay.Friday);
		
		
		IdentityHashMap<String, Employee> identityHashMap=new IdentityHashMap<>(eMap);
		identityHashMap.put(new String("147-654-125"), new Employee("Peter Park")); //可以再次插入
		System.out.println(identityHashMap);
		
		
		
		
		String[] names=new String[]{"849","987","986","123","345"};
		List<String> lnames=Arrays.asList(names);
		
//		try{
//			lnames.add("7653"); //这里 asList 只是返回一个视图，修改操作时不行的。
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
		
		
		
//		ArrayList<String> aadd=new ArrayList<>();
//		aadd.add("OOO");
//		
//		
//		Set<String> nSet=Collections.singleton("Name");
//		
//		String[] valuses=aadd.toArray(new String[0]);
		
		
		
		System.out.println(lnames);
		Collections.sort(lnames);
		int idx=Collections.binarySearch(lnames, "987");
		System.out.println("index of 987 is "+ idx+", value:"+(idx>=0? ", value:"+lnames.get(idx):" null"));
		
		
		Collections.sort(lnames, Collections.reverseOrder());
		System.out.println(Arrays.toString(names));
		System.out.println(lnames);
		
		
		int idx2=Collections.binarySearch(lnames, "987");
		System.out.println("index of 987 is "+ idx2+ (idx2>=0? ", value:"+lnames.get(idx2):" null"));
		
		
		
		
		Hashtable<String, Employee> hashtableEmp=new Hashtable<>();
		hashtableEmp.put("123-78-95", new Employee("Jet"));// 同步的
		
		
		Stack<Employee> stackEmp=new Stack<>();
		stackEmp.push(new Employee("Brows"));
		
		
		BitSet biSet=new BitSet(20);
		biSet.set(1);
		
		BitSet bitSet2=new BitSet();
		bitSet2.set(3);
		bitSet2.set(2);;
		
		System.out.println(biSet.intersects(bitSet2));
		biSet.or(bitSet2);
		
		System.out.println();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}


enum WeekDay { Monday, Tuesday, Wednesday, Tuhrsday, Friday, Saturday, Sunday };


class Employee{
	
	public Employee (String name) {
		this.name=name;
	}
	
	private String name;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String toString()
	{
		return "Employee "+this.name;
	}
	
	
}















