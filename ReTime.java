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
@WebServlet("/retime")

public class ReTime extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Hour=req.getParameter("hour");	
		String Min=req.getParameter("min");
		String member=req.getParameter("name");
		String time=req.getParameter("time");
		
		int hour=Integer.parseInt(Hour);
		
		int min=Integer.parseInt(Min);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTf-8");
		PrintWriter out=resp.getWriter();
		ArrayList data=Time.calculateFreeTimeList(getData(Database.getConnection(),member,time), hour, min);
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
		
	}
	static	Map getData(Connection con,String member,String Time)
	{
	Map<String,String>ab=new HashMap<String,String>();
	try {
		PreparedStatement pr=con.prepareStatement("SELECT * FROM event WHERE toUser='"+member+"' AND TIME!='"+Time+"' ORDER BY TIME ASC");
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
	


}
