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

@WebServlet("/reg")
public class Register extends HttpServlet{
	Connection con;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=req.getParameter("fname");
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		String sal=req.getParameter("sal");
		String password=req.getParameter("password");
		
	resp.setContentType("application/json");
	resp.setCharacterEncoding("UTf-8");
	PrintWriter out=resp.getWriter();
	try {
		Class.forName("com.mysql.jdbc.Driver");
	//	System.out.println("load");
		Connection connection =Database.getConnection();
				
		//System.out.println("create connection");
		
		String qry="insert into emp_details values('"+name+"','"+email+"','"+phone+"','"+sal+"','"+password+"')";
		PreparedStatement pr=connection.prepareStatement(qry);
		pr.execute();
		String qry1="select * from emp_details";
		PreparedStatement pr1=connection.prepareStatement(qry1);
		ResultSet rs=pr1.executeQuery();
		
				JSONArray  jj=new JSONArray();
		
		//JSONObject ff=new JSONObject();
		while(rs.next())
		{JSONObject ff=new JSONObject();
			ff.put("name", rs.getString(1));
			ff.put("email", rs.getString(2));
			ff.put("phone", rs.getString(3));
			ff.put("sal", rs.getString(4));
			ff.put("password", rs.getString(5));
			jj.put(ff);
		
			
		}
		out.println(jj);
		//out.flush();
		
		rs.close();
		pr.close();
		//con.close();
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}



}
