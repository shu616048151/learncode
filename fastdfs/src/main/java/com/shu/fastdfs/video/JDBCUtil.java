package com.shu.fastdfs.video;
 
import com.shu.fastdfs.imagesync.ImageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class JDBCUtil {

    private static final Logger logger= LoggerFactory.getLogger(JDBCUtil.class);
    private static Connection conn=null;

    static {
        String username="root";
        String password="123";
        String url="jdbc:mysql://localhost:3306/smallmovie?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String driver="com.mysql.cj.jdbc.Driver";
        //数据库连接
        //第一步：加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //第二步：建立数据库连接
        try {
             conn=DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	//数据库连接
	public static Connection conn() throws Exception{
		String username="root";
		String password="123";
		String url="jdbc:mysql://localhost:3306/smallmovie?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
//		String driver="com.mysql.jdbc.Driver";
		String driver="com.mysql.cj.jdbc.Driver";
		//数据库连接
		//第一步：加载驱动
		Class.forName(driver);
		//第二步：建立数据库连接
		Connection conn=DriverManager.getConnection(url,username,password);
		return conn;
	}


    /**
     * 上传图像到fastdfs
     * @throws Exception
     */
    public static void insertBili(Bili bili ) throws Exception{
        Statement st = conn.createStatement();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("insert into bili(username,platform,mid,aid,title,url,play,upload_time,time_length,description,photo_thumb) ")
                .append("Values(")
                .append("'"+bili.getAuthor()+"',")
                .append("'"+3+"',")
                .append("'"+bili.getMid()+"',")
                .append("'"+bili.getAid()+"',")
                .append("'"+bili.getTitle()+"',")
                .append("'"+bili.getUrl()+"',")
                .append("'"+bili.getPlay()+"',")
                .append("'"+simpleDateFormat.format(bili.getUploadTime())+"',")
                .append("'"+bili.getLength()+"',")
                .append("'"+bili.getDescription()+"',")
                .append("'"+bili.getPic()+"'")
//                .append("'"+bili.getCreateTime().getTime()+"'")
                .append(")");
        System.out.println(stringBuilder.toString());
        boolean execute = st.execute(stringBuilder.toString());
        logger.info("插入数据库成功");
    }


    /**
     * 上传图像到fastdfs
     * @throws Exception
     */
    public static void insertXiGua(Bili bili ){
        Statement st = null;
        try {
            st = conn.createStatement();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("insert into xigua(username,platform,mid,aid,title,url,play,upload_time,time_length,description,photo_thumb) ")
                    .append("Values(")
                    .append("'"+bili.getAuthor()+"',")
                    .append("'"+3+"',")
                    .append("'"+bili.getMid()+"',")
                    .append("'"+bili.getAid()+"',")
                    .append("'"+bili.getTitle()+"',")
                    .append("'"+bili.getUrl()+"',")
                    .append("'"+bili.getPlay()+"',")
                    .append("'"+simpleDateFormat.format(bili.getUploadTime())+"',")
                    .append("'"+bili.getLength()+"',")
                    .append("'"+bili.getDescription()+"',")
                    .append("'"+bili.getPic()+"'")
//                .append("'"+bili.getCreateTime().getTime()+"'")
                    .append(")");
            System.out.println(stringBuilder.toString());
            boolean execute = st.execute(stringBuilder.toString());
            logger.info("插入数据库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}