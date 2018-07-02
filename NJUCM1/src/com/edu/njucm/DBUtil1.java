package com.edu.njucm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil1 {
	public void DBUtil1() {
//		String url="jdbc:mysql://localhost:3306/njdb";
//		String user="root";
//		String password="hu686868";
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			DriverManager.getConnection(url, user, password);
//			System.out.println("连接成功！");
//			System.out.println("加载成功！");
//		} catch (Exception e) {
//			// TODO: handle exception
//		System.out.println("加载失败！");
//			e.printStackTrace();
//		}
//		testQuery2();
		
//		testDelete(2);
//		testUpdate(1);
		testQuery1();
	}
	
	public static void testAdd(){
//		String sql="insert into student values("+student.getId()+",'"+student.getName()+"',"+"'"+student.getPassword()+"'s)";
		String sql="insert into emp "+"values(50,'丁志伟','男','student',12345,6666)";
		Connection connection=null;
		Statement statement=null;
		try{
			//connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/njdb?characterEncoding=utf8", "root", "hu686868");
			connection=jdbcTools.connection();
			statement=(Statement) connection.createStatement();
			statement.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcTools.release(connection, statement, null);
		}
		System.out.println("成功！");
	}
	public static void testDelete(int a){
		String sql="delete from emp where id="+a;
		jdbcTools.update(sql);
		System.out.println("成功！");
	}
	public static void testUpdate(int a){
		String sql="update emp set name='"+"hanyue"+"'"+"where id="+a;
		jdbcTools.update(sql);
		System.out.println("成功！");
	}
	public static void testQuery1() {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		//结果集
		ResultSet resultSet=null;
		try{
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//通过驱动管理类获取数据库连接
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/njdb","root","hu686868");
			String sql="select * from emp where id=?";
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, "1");
			//向数据库发出sql执行查询，查询出结果集
			resultSet=preparedStatement.executeQuery();
			//遍历查询结果集
			while(resultSet.next()){
				System.out.println(resultSet.getString("name")+resultSet.getString("sex")+"..."+resultSet.getString("job")+resultSet.getString("sal")+resultSet.getString("bonus"));
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(resultSet!=null){
				try{
						resultSet.close();
					}catch (SQLException e) {
					// TODO: handle exception
						e.printStackTrace();
				}
			}
			if(preparedStatement!=null){
				try{
					preparedStatement.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try{
					connection.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}	
		}
	}
	public static void testQuery2(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
		//加载数据库驱动
		Class.forName("com.mysql.jdbc.Driver");
		//通过驱动管理类获取数据库连接
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/njdb","root","hu686868");
		String sql="select * from student where id=?";
		ps =conn.prepareStatement(sql);
		ps.setString(1, "2");
		//向数据库发出sql执行查询，查询出结果集
		rs=ps.executeQuery();
		//遍历查询结果集
		while(rs.next()){
			System.out.println(rs.getString("name")+"..."+rs.getString("password"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		jdbcTools.release(conn, ps, rs);
	}
	}
	public void play1() {
		System.out.println("wozuishuai");
	}
}
