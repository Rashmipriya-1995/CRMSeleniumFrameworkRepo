package com.crm.generic.javaUtility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber()
	{
		Random random =new Random();
		int randomNumber=random.nextInt(5000);
		return randomNumber;
	}
	
	public String getSystemCurrentDate()
	{
		Date dateobj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateobj);
		return date;
	}
	
	public String getRequiredDate(int Days)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal=sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, Days);
		String reqDate=sdf.format(cal.getTime());
		return reqDate;
	}
	
	
	public String getSystemTime() {
		
		return LocalDateTime.now().toString().replace(":", "-");
	}


}
