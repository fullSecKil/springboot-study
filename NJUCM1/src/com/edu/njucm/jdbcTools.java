package com.edu.njucm;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

//import com.mysql.jdbc.Statement;

public class jdbcTools {
	public static void release(Connection connection,Statement statement,ResultSet resultSet){
		if(connection!=null){
			try{
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try{
				statement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(resultSet!=null){
			try{
				resultSet.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static Connection connection() throws Exception{
		Properties properties=new Properties();
		InputStream inputStream=jdbcTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(inputStream);
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
		String driverClass=properties.getProperty("driverClass");
		String url=properties.getProperty("url");
		Class.forName(driverClass);
		Connection connection=DriverManager.getConnection(url, user, password);
		return connection;
	}
	public static void update(String sql){
		Connection connection=null;
		Statement statement=null;
		try{
			connection=jdbcTools.connection();
			statement=connection.createStatement();
			statement.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcTools.release(connection, statement, null);
		}
	}
}
