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

@WebServlet("/psval")

public class PassValidation extends HttpServlet{
	Connection con;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String password=req.getParameter("password");	
		String email=req.getParameter("email");
	resp.setContentType("application/json");
	resp.setCharacterEncoding("UTf-8");
	PrintWriter out=resp.getWriter();
	try {
		Class.forName("com.mysql.jdbc.Driver");
	//	System.out.println("load");
		Connection connection =Database.getConnection();
			
		//System.out.println("create connection");
		
		//String qry="insert into emp_details values('"+name+"','"+email+"','"+phone+"','"+sal+"','"+password+"')";
		//PreparedStatement pr=connection.prepareStatement(qry);
		//pr.execute();
		String qry1="select * from emp_details where emai='"+email+"'  and password='"+password+"'";
		PreparedStatement pr1=connection.prepareStatement(qry1);
		ResultSet rs=pr1.executeQuery();
		
				JSONArray  jj=new JSONArray();
		
		//JSONObject ff=new JSONObject();
		if(rs.next())
		{
			out.print("success");
		}
		else
		out.println("error");
		//out.flush();
		
		rs.close();
		pr1.close();
		//con.close();
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}


}
