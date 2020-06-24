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
@WebServlet("/member")

public class MemberDetails extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTf-8");
		PrintWriter out=resp.getWriter();
		String name=req.getParameter("name");
		ArrayList <String>data=getData(Database.getConnection(),name);
		JSONArray  jj=new JSONArray();
		

		for(String s:data)
		{
		
			JSONObject ff=new JSONObject();
			ff.put("member", s);
			jj.put(ff);
		}
		out.print(jj);
		
	}
	

static	ArrayList<String> getData(Connection con,String name)
	{
	ArrayList<String> aa=new ArrayList<String>();
	try {
		PreparedStatement pr=con.prepareStatement("SELECT * FROM emp_details WHERE NAME NOT IN ('"+name+"')");
		ResultSet rs=pr.executeQuery();
		while(rs.next())
		{aa.add(rs.getString(1));
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return aa;
	
	}
	

}
