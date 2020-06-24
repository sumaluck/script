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

@WebServlet("/eval")

public class EmailVaildate extends HttpServlet{
	Connection con;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email=req.getParameter("email");		
	resp.setContentType("application/json");
	resp.setCharacterEncoding("UTf-8");
	PrintWriter out=resp.getWriter();
	try {
		Class.forName("com.mysql.jdbc.Driver");
	
		Connection connection =Database.getConnection();
				
		String qry1="select * from emp_details where emai='"+email+"'";
		PreparedStatement pr1=connection.prepareStatement(qry1);
		ResultSet rs=pr1.executeQuery();
		
				JSONArray  jj=new JSONArray();
		if(rs.next())
		{
			out.print("success");
		}
		else
		out.println("error");
		
		rs.close();
		pr1.close();
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}

