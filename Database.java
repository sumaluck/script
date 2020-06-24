package com.Org;
import java.sql.*;
public class Database {
	static String url="jdbc:mysql://localhost:3306/";
	static String user="root";
	static String password="12345";
	static String dbName="btm";
	static Connection getConnection()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url+dbName+"?user="+user+"&password="+password+"");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		String s="select * from event";
		try {
			PreparedStatement pr=getConnection().prepareStatement(s);
			ResultSet rs=pr.executeQuery();
			/*while(rs.next())
			{
				System.out.println(rs.getString(1));
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
