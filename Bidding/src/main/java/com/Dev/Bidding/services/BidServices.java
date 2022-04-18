package com.Dev.Bidding.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.Dev.Bidding.model.Bid;

@Component
public class BidServices {
	
	
	public String getPhaseTime()
	{
		switch(getPhase()) {
		  case 1:
		    return "At 7 am";
		    
		  case 2:
			  return "At 8 am";
		    
		  case 3:
			    return "At 9 am";
			    
			  case 4:
				  return "At 10 am";
			    
			  case 5:
				    return "At 11 am";
				    
				  case 6:
					  return "At 12 pm";
				    
				  case 7:
					    return "At 1 pm";
					    
					  case 8:
						  return "At 2 pm";
					  case 9:
						  return "At 3 am";
					    
					  case 10:
						    return "At 4 pm";
						    
						  case 11:
							  return "At 5 pm";
						  case 12:
							  return "At 6 pm";
					    
		  default:
		   return "Invalid";
		}
		
	}
	public int getPhase()
	{
		int phase=0;
		Calendar cal = Calendar.getInstance();
		Date date=cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
		String time=dateFormat.format(date);
		
		String[] st = time.split(":");
		int hh=0;
		int mm=0;
		int ss=0;
		hh=Integer.valueOf(st[0]);
		mm=Integer.valueOf(st[1]);
		ss=Integer.valueOf(st[2]);
		

		if((hh >= 6 && mm >= 0 && ss >=0)	&& (hh < 7 && mm <= 59 && ss <=59))
		{
			phase=1;
		}
		else if((hh >= 7 && mm >= 0 && ss >=0)	&& (hh < 8 && mm <= 59 && ss <=59))
		{
			phase=2;
		}
		else if((hh >= 8 && mm >= 0 && ss >=0)	&& (hh < 9 && mm <= 59 && ss <=59))
		{
			phase=3;
		}
		if((hh >= 9 && mm >= 0 && ss >=0)	&& (hh < 10 && mm <= 59 && ss <=59))
		{
			phase=4;
		}
		else if((hh >= 10 && mm >= 0 && ss >=0)	&& (hh < 11 && mm <= 59 && ss <=59))
		{
			phase=5;
		}
		else if((hh >= 11 && mm >= 0 && ss >=0)	&& (hh < 12 && mm <= 59 && ss <=59))
		{
			phase=6;
		}
		
		if((hh >= 12 && mm >= 0 && ss >=0)	&& (hh < 13 && mm <= 59 && ss <=59))
		{
			phase=7;
		}
		else if((hh >= 13 && mm >= 0 && ss >=0)	&& (hh < 14 && mm <= 59 && ss <=59))
		{
			phase=8;
		}
		else if((hh >= 14 && mm >= 0 && ss >=0)	&& (hh < 15 && mm <= 59 && ss <=59))
		{
			phase=9;
		}
		else if((hh >= 15 && mm >= 0 && ss >=0)	&& (hh < 16 && mm <= 59 && ss <=59))
		{
			phase=10;
		}
		if((hh >= 16 && mm >= 0 && ss >=0)	&& (hh < 17 && mm <= 59 && ss <=59))
		{
			phase=11;
		}
		else if((hh >= 17 && mm >= 0 && ss >=0)	&& (hh < 18 && mm <= 59 && ss <=59))
		{
			phase=12;
		}
		return phase;
	}
}
