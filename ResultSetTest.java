package com.Org;

import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResultSetTest {
	public static void main(String[] args) {
		for(int i=1;i<5;i++) {
			Receive(Result());
		
		}
	}
	static void Receive(Map mm)
	{Map<Integer,Map> m=mm;
	for(int i=0;i<m.size();i++) {
		Map <Integer,String> ab=m.get(i);
		
			System.out.println(i+" ss"+ab.get(0)+" "+ab.get(1)+" "+ab.get(2));
			
			
				}
	}
static Map Result() {
	Map <Integer,Map> mm=new HashMap();
	int i=0;
	try {
		Connection con=Database.getConnection();
		PreparedStatement pr=con.prepareStatement("select * from chart ");
		ResultSet rs=pr.executeQuery();
	
		while(rs.next()) {
			int j=0;
			Map <Integer,String>m=new HashMap();
			m.put(j++, rs.getInt(1)+"");
			m.put(j++, rs.getInt(2)+"");
			m.put(j++, rs.getFloat(3)+"");
			mm.put(i++, m);
			
		}
		rs.close();
		pr.close();
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return mm;
}
}
