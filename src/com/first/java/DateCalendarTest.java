package com.first.java;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EventListenerProxy;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateCalendarTest {

	public static void main(String[] args) throws IOException{
	
		Locale.setDefault(Locale.CHINESE);
		
		
			GregorianCalendar gCalendar=new GregorianCalendar(2014, Calendar.DECEMBER, 31, 12,43);
			System.out.println(gCalendar);
			
			SimpleDateFormat sfDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			gCalendar.set(2015, 11, 20,13,59,58);
			
			gCalendar.set(Calendar.DAY_OF_MONTH, 21);
			gCalendar.set(Calendar.MONTH, Calendar.APRIL);
			System.out.println(gCalendar.get(Calendar.DAY_OF_MONTH));
			
			System.out.println(gCalendar.get(Calendar.MONTH));
			System.out.println(sfDateFormat.format(gCalendar.getTime()));
			
	
			
			
			GregorianCalendar d=new GregorianCalendar();
			int today=d.get(Calendar.DAY_OF_MONTH);
			int month=d.get(Calendar.MONTH);
			
			//set start date of the month;
			d.set(Calendar.DAY_OF_MONTH,1);
			
			int weekday=d.get(Calendar.DAY_OF_WEEK);
			
			// get the first day of week (sunday in the US)
			int firstDayofWeek=d.getFirstDayOfWeek();
			
			//determine the required indentation for the first line.
			int indent=0;
			while(weekday!=firstDayofWeek){
				indent++;
				d.set(Calendar.DAY_OF_MONTH,-1);
				weekday=d.get(Calendar.DAY_OF_WEEK);
			}
			
			
			// print weekday names
			String[] weekdayNames=new DateFormatSymbols().getShortWeekdays();
			do{
				System.out.printf("%4s", weekdayNames[weekday]);
				d.add(Calendar.DAY_OF_MONTH, 1);
				weekday=d.get(Calendar.DAY_OF_WEEK);
			}while(weekday!=firstDayofWeek);
			System.out.println();
			
			
			for(int i=0;i<=indent;i++)
			{
				System.out.print("   ");
			}
			
			d.set(Calendar.DAY_OF_MONTH,1);
			do {
				int day=d.get(Calendar.DAY_OF_MONTH);
				System.out.printf("%3d", day);
				
				if(day==today){
					System.out.print("*   ");
				}
				else{
					System.out.print("    ");
				}
				
				d.add(Calendar.DAY_OF_MONTH, 1);
				weekday=d.get(Calendar.DAY_OF_WEEK);
				if(weekday==firstDayofWeek){
					System.out.println(); // start a new line
				}			
			} while (d.get(Calendar.MONTH)==month);
			
			
			// print final end of line if nessary
			if(weekday!=firstDayofWeek){
				System.out.println();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
	}
	
	
	
}
