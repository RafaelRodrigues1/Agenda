package model.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() {
		Connection con = null;		
		try {
			Properties prop = getProperties();
			String url = getProperties().getProperty("url");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, prop);
			return con;
		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DBException(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			throw new DBException(ex.getMessage());
		}
	}
	
	private static void closeConnection(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException(ex.getMessage());
			}
		}
	}
	
	public static void closeConnection(Connection con, Statement sta) {
		if(sta != null) {
			try {
				sta.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException(ex.getMessage());
			}
		}
		closeConnection(con);
	}
	
	public static void closeConnection(Connection con, Statement sta, ResultSet res) {
		if(res != null) {
			try {
				res.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new DBException(ex.getMessage());
			}
		}
		closeConnection(con, sta);
	}

	private static Properties getProperties() {
		try(FileInputStream fis = new FileInputStream(new File(""
				+ "C:\\Users\\RAFAEL\\eclipse-workspace\\Agenda\\src\\main\\java\\model\\database\\connection.properties"))){
			Properties prop = new Properties();
			prop.load(fis);
			return prop;
		}catch(IOException ex) {
			ex.printStackTrace();
			throw new DBException(ex.getMessage());
		}
	}
}
