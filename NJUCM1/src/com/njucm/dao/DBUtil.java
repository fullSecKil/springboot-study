package com.njucm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//封装数据库操作的各种功能
public class DBUtil {
    //1.先定义各变量、对象
    String driver="com.mysql.jdbc.Driver";//数据库驱动
    String url="jdbc:mysql://localhost:3306/njdb?useSSL=false";//六要素之四要素
    String user="root";//六要素之一要素
    String password="123456";//六要素之一要素

    //4个对象（任选其三）
    Connection conn=null;
    Statement st=null;
    PreparedStatement ps=null;
    ResultSet	rs=null;

    //若干语句
    String sq11="";
    String sq12="";
    String sq13="";
    String sq14="";

    //其他变量
    int flag1=0;
    boolean flag2=false;


//2.按业务需求，封装不同的功能

    //2.1封装连接功能
    public Connection getConn() throws Exception{
        //将六步法中的前两步统一封装再此方法中
        try {
            Class.forName(driver);
            System.out.println("加载成功！");
        }catch (ClassNotFoundException e) {
            //异常处理1
            e.printStackTrace();
        }


        try {
            conn=DriverManager.getConnection(url,user,password);
            System.out.println("加载成功！");
            //通过六要素建立连接，获得连接对象
        }catch (SQLException e) {
            //异常处理2
            e.printStackTrace();
        }
        return conn;
    }


    public void play1() {
        System.out.println("与对象一起去浪迹天涯");
    }

//2.2封装关闭功能

//2.3封装增加功能

//2.4封装修改功能

//2.5封装删除功能

//2.6封装查询功能 *****
}