package com.Org;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
@WebServlet("/time")


public class Time extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Hour=req.getParameter("hour");	
		String Min=req.getParameter("min");
		String member=req.getParameter("name");
		
		int hour=Integer.parseInt(Hour);
		
		int min=Integer.parseInt(Min);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTf-8");
		PrintWriter out=resp.getWriter();
		ArrayList data=calculateFreeTimeList(getData(Database.getConnection(),member), hour, min);
		JSONArray  jj=new JSONArray();
		

		for(Object s:data)
		{for(String p:(ArrayList<String>)s)
		{
			JSONObject ff=new JSONObject();
			ff.put("time", p);
			jj.put(ff);
		}
		
		}
		
		
		
		
				out.print(jj);
//		for(Object s:data)
//		{for(String p:(ArrayList<String>)s)
//			System.out.println(p);
//		}
		
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String starttime="09:30 am";
		String endtime="18:00 pm";
		
	
		int hour=sc.nextInt();
		int min=sc.nextInt();
		String member=sc.next();
		
		ArrayList data=calculateFreeTimeList(getData(Database.getConnection(),member), hour, min);
		//ArrayList data=spindata(hour, min, getCal(starttime), getCal(endtime));
		
		for(Object s:data)
		{for(String p:(ArrayList<String>)s)
			System.out.println(p);
		}
		
	}
	static Calendar getCal(String time)
	{
		//String time="02:30 pm";
		  SimpleDateFormat so=new SimpleDateFormat("HH:mm a");
		  Date d = null;
		try {
			d=so.parse(time);
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	SimpleDateFormat s1=new SimpleDateFormat("hh");
	String hour=new SimpleDateFormat("HH").format(d);
	String min=new SimpleDateFormat("mm").format(d);
	//System.out.println(hour+""+min);
	 Calendar c=Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour));
    c.set(Calendar.MINUTE,Integer.parseInt(min));
    c.set(Calendar.SECOND,00);
	
		return c;
	}

static	Map getData(Connection con,String member)
	{
	Map<String,String>ab=new HashMap<String,String>();
	try {
		PreparedStatement pr=con.prepareStatement("SELECT * FROM event WHERE toUser='"+member+"' ORDER BY TIME ASC");
		ResultSet rs=pr.executeQuery();
		while(rs.next())
		{ab.put(rs.getString(3),rs.getString(7));
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ab;
	
	}
	
	
static	ArrayList<Integer> calculateFreeTime(int duration)
	{
		ArrayList <Integer>mettingTime=new ArrayList();
		for(int i=9;i<18;i=i+duration)
		{
			int availableTime=i+duration;
			if(availableTime<=18)
			{
				mettingTime.add(i);
			}
		}
		return mettingTime;
	}
static ArrayList<Integer> calculatePreviousTime(int defaultStart,int startTime ,int  duration)
{
ArrayList <Integer> calculate=new ArrayList<Integer>();
for(int i=defaultStart;i<startTime;i++)
{
int available=i+duration;
if(available<=startTime)
	calculate.add(i);
}
return calculate;
}
static ArrayList<Integer> getFinalList(ArrayList<Object> list)
{ArrayList<Integer> aa=new ArrayList<Integer>();

for(Object obj:list)
{
	ArrayList<Integer>findata=(ArrayList<Integer>)obj;
	if(findata.isEmpty());
	else
	{
		for(int i:findata)
		{
			aa.add(i);
		}
	}
	
	
}
return aa;
	}

static ArrayList calculateFreeTimeList(Map<String,String>previousTime,int hour,int min)
{

	
//	if(previousTime.isEmpty())
//	{
//		System.out.println("null value");
//		return calculateMettingTime;
//	}
	String defaultStart="09:00 am";
	String defaultEnd="18:00 pm";
	String superEnd = null;
	ArrayList<Object>ab=new ArrayList<Object>();
	for(Map.Entry<String, String> data:previousTime.entrySet())
	{
		String startTime=data.getKey();
		String endTime=data.getValue();
		ArrayList<String> listdata=spindata(hour,min,getCal(defaultStart),getCal(startTime));
		ab.add(listdata);
		defaultStart=endTime;
		superEnd=endTime;
	}
	ArrayList<String> lastdata=spindata(hour,min, getCal(defaultStart), getCal(defaultEnd));
	ab.add(lastdata);
	//ArrayList<Integer>abc=getFinalList(ab);
	
		return ab;
	
	}
static ArrayList<String> spindata(int hour,int min,Calendar c,Calendar e) {
	ArrayList<String>abc=new ArrayList<String>();
	
	int ihour=hour;

    int imin=min;
    Date d=new Date();
 //System.out.println(c.get(Calendar.HOUR_OF_DAY)+" df "+e.get(Calendar.HOUR_OF_DAY));
    
    while((c.get(Calendar.HOUR_OF_DAY)*60)+c.get(Calendar.MINUTE)+(ihour*60)+imin<=e.get(Calendar.HOUR_OF_DAY)*60) {
   //	 while((c.get(Calendar.HOUR_OF_DAY)*60)<e.get(Calendar.HOUR_OF_DAY)*60) {
    
    	   // System.out.println((c.get(Calendar.HOUR_OF_DAY)*60)+c.get(Calendar.MINUTE)+(ihour*60)+imin+"   "+e.get(Calendar.HOUR_OF_DAY)*60);
        d = c.getTime();
       // SimpleDateFormat ss=new SimpleDateFormat("e dd mmm yy  HH:mm:ss");
        SimpleDateFormat so=new SimpleDateFormat(" E dd MMM YYYY HH:mm a");
        String s=so.format(d);
       

       // String s=d.toString();
        abc.add(s);
       // System.out.println(s);

        c.add(Calendar.HOUR, ihour);
        c.add(Calendar.MINUTE, imin);



    }
	
	
	return abc;
}




}