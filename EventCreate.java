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

@WebServlet("/eCreate")


public class EventCreate  extends HttpServlet {
	Connection con;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String toUser=req.getParameter("toUser");
		String fromUser=req.getParameter("fromUser");
		String tital=req.getParameter("tital");	
		String date=req.getParameter("date");
		String time=req.getParameter("time");
		String endtime=req.getParameter("endtime");
		String duration=req.getParameter("duration");
		String location=req.getParameter("location");
		String details=req.getParameter("details");
	resp.setContentType("application/json");
	resp.setCharacterEncoding("UTf-8");
	PrintWriter out=resp.getWriter();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = Database.getConnection();
				
		String qry1="Insert into event values('"+tital+"','"+date+"','"+time+"','"+duration+"','"+location+"','"+details+"','"+endtime+"','"+toUser+"','"+fromUser+"','Pending' )";
		PreparedStatement pr1=connection.prepareStatement(qry1);
	pr1.execute();
		
				JSONArray  jj=new JSONArray();	
			out.print("success");
	
		pr1.close();
		//con.close();
		
	} catch (ClassNotFoundException | SQLException e) {
		out.println("error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}



}
