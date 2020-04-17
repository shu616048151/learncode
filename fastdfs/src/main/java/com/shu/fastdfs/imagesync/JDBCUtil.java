package com.shu.fastdfs.imagesync;
 
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
 
 
	//数据库连接
	public static Connection conn() throws Exception{
		String username="root";
		String password="123";
		String url="jdbc:mysql://localhost:3306/change";
		String driver="com.mysql.jdbc.Driver";
		//数据库连接
		//第一步：加载驱动
		Class.forName(driver);
		//第二步：建立数据库连接
		Connection conn=DriverManager.getConnection(url,username,password);
		return conn;
	}
	
}