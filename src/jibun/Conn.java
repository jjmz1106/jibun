package jibun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection open() {
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","test","test"); 
			con.setAutoCommit(false);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
