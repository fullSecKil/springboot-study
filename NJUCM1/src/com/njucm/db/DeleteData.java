package com.njucm.db;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;

//利用ps完成数据的删除（删除最后1条记录）
public class DeleteData {
	public void delete() throws Exception {
		//若干变量、对象
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/njdb?useSSL=false";
		String user="root";
		String password="123456";
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="delete from emp where id=?";//增加数据语句，预设占位符

		//定义一个整数标记flag，跟踪数据处理的情况

		//1.加载驱动
		Class.forName(driver);
		
		//2.创建连接对象，产生conn
		DriverManager.getConnection(url,user,password);
	
		//3.创建语句对象，产生ps
		ps= (PreparedStatement) conn.prepareStatement(sql);
	
		//4.执行SQL语句，产生rs或整数flag
		//解决占位符赋值问题
		int ids=0;	String names="xuerui", gender="nan" ,jobs="nongming";
		float sals=0.01f, bonus=1f;
		ps.setInt(1,ids);//第一个占位符-编号
		ps.setString(2, names);//第二个占位符-姓名
		ps.setString(3, gender);//第三个占位符-性别
		ps.setString(4,jobs);//第四个占位符-职业
		ps.setFloat(5, sals);//第五个占位符-工资
		ps.setFloat(6, bonus);//第六个占位符-奖金
		//再执行
		int flag=ps.executeUpdate();
	
		//5.输出结果（可省）
		if (flag>0) {
			System.out.println("删除数据成功！");
			System.out.println("删除了"+flag+"条记录");	
		} else {
			System.out.println("删除数据失败!");
		}
	
		//6.关闭对象，释放资源
		if(ps!=null) {
			ps.close();
		}
		
		if(conn!=null) {
			conn.close();
		}
		
	}
	
}
