package com.Org;

import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
@WebServlet("/fs")
public class First extends HttpServlet {
Connection con;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	resp.setContentType("application/json");
	resp.setCharacterEncoding("UTf-8");
	PrintWriter out=resp.getWriter();
	try {
		Class.forName("com.mysql.jdbc.Driver");
	//	System.out.println("load");
		Connection connection = Database.getConnection();
			//	DriverManager.getConnection("jdbc:mysql://localhost:3306/btm?user=root&password=12345");
		//System.out.println("create connection");
		
		String qry="select * from chart";
		PreparedStatement pr=connection.prepareStatement(qry);
		ResultSet rs=pr.executeQuery();
				JSONArray  jj=new JSONArray();
		
		//JSONObject ff=new JSONObject();
		while(rs.next())
		{JSONObject ff=new JSONObject();
			ff.put("id", rs.getString(1));
			ff.put("year", rs.getString(2));
			ff.put("growth_rate", rs.getString(3));
			jj.put(ff);
		
			
		}
		out.println(jj);
		//out.flush();
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
