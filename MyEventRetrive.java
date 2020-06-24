package com.Org;
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

@WebServlet("/eRetrive1")

public class MyEventRetrive extends HttpServlet {
	Connection con;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	String email=req.getParameter("email");	
//		String date=req.getParameter("date");
//		String time=req.getParameter("time");
//		String duration=req.getParameter("duration");
//		String location=req.getParameter("location");
//		String details=req.getParameter("details");
	resp.setContentType("application/json");
	resp.setCharacterEncoding("UTf-8");
	PrintWriter out=resp.getWriter();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection =Database.getConnection();
				
		String qry1="SELECT * FROM event WHERE toUser=(SELECT NAME FROM emp_details WHERE emai='"+email+"')";
		PreparedStatement pr1=connection.prepareStatement(qry1);
	ResultSet rs=pr1.executeQuery();
		
				JSONArray  jj=new JSONArray();
				
		
		//JSONObject ff=new JSONObject();
		
		while(rs.next())
		{JSONObject ff=new JSONObject();
		//ff.getJ
		ff.put("tital", rs.getString(1));
		ff.put("date", rs.getString(2));
		ff.put("time", rs.getString(3));
		ff.put("duration", rs.getString(4));
		ff.put("location", rs.getString(5));
		ff.put("details", rs.getString(6));
		ff.put("toUser", rs.getString(8));
		ff.put("fromUser", rs.getString(9));
		ff.put("status", rs.getString(10));
		jj.put(ff);
	
				}
		out.print(jj);
		
	
		//out.flush();
		
		rs.close();
		pr1.close();
		//con.close();
		
	} catch (ClassNotFoundException | SQLException e) {
		out.println("error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}


}
