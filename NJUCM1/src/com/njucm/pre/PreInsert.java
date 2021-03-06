package com.njucm.pre;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;

//利用ps完成数据的增加
public class PreInsert {
	public void preInse() throws Exception {
		//若干变量、对象
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/njdb?useSSL=false";
		String user="root";
		String password="123456";
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="insert into emp values(?,?)";//增加数据语句，预设占位符
		

		
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
		int flag=ps.executeUpdate();	
		//5.输出结果（可省）
		if (flag>0) {
			System.out.println("增加数据成功！");
			System.out.println("增加了"+flag+"条记录");		
		} else {
			System.out.println("增加数据失败!");
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
