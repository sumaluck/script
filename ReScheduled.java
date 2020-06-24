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

@WebServlet("/rescheduled")

public class ReScheduled extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String preUser=req.getParameter("preUser");
		String preTime=req.getParameter("preTime");
		String toUser=req.getParameter("toUser");
		//String fromUser=req.getParameter("fromUser");
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
		Connection connection =Database.getConnection();
				
		String qry1="UPDATE event SET tital='"+tital+"',DATE='"+date+"',TIME='"+time+"',duration='"+duration+"',location='"+location+"',details='"+details+"',endtime='"+endtime+"',toUser='"+toUser+"',STATUS='Pending ' WHERE toUser='"+preUser+"' AND TIME='"+preTime+"'";  
				PreparedStatement pr1=connection.prepareStatement(qry1);
	pr1.execute();
		
				JSONArray  jj=new JSONArray();
		
		//JSONObject ff=new JSONObject();
		
		
			out.print("success");
		
	
		//out.flush();
		
		
		pr1.close();
		//con.close();
		
	} catch (ClassNotFoundException | SQLException e) {
		out.println("error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
