package pl.mycompany.conectorprovider;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConectorProvider {
	private static DataSource dataSource;
	
	public static Connection getConnection() throws SQLException {
		return getDSInstance().getConnection();
	}
	
	private static DataSource getDSInstance() {
		try {
		if(dataSource == null) {
			Context contextInitial = new InitialContext();
			Context envContext = (Context) contextInitial.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/mycompany");
		}
		
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
		return dataSource;
	}
}
