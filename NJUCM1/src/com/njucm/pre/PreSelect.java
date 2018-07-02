package com.njucm.pre;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;

//利用ps完成数据的查询（查询所有女员工）
public class PreSelect {
	public void preSele() throws Exception {
		//若干变量、对象
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/njdb?useSSL=false";
		String user="root";
		String password="123456";
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select * from emp where asl>=?";//增加数据语句，预设占位符

		//定义一个整数标记flag，跟踪数据处理的情况

		//1.加载驱动
		Class.forName(driver);
		
		//2.创建连接对象，产生conn
		DriverManager.getConnection(url,user,password);
	
		//3.创建语句对象，产生ps
		ps= (PreparedStatement) conn.prepareStatement(sql);
	
		//4.执行SQL语句，产生rs或整数flag
		//解决占位符赋值问题
		ps.setInt(1,3);//第一个占位符-编号
		ps.setString(2, "周华健");//第二个占位符-姓名
		ps.setString(3, "女");
		ps.setString(4, "歌星");
		ps.setFloat(5, 8888.88f);
		ps.setFloat(6, 3333.33f);		
		//再执行

		//5.输出结果（可省）
		int flag=ps.executeUpdate();
		if (flag>0) {
			System.out.println("查询数据成功！");
			System.out.println("查询了"+flag+"条记录");		
		} else {
			System.out.println("查询数据失败!");
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
