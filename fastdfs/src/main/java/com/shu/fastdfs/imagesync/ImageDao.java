package com.shu.fastdfs.imagesync;

import org.assertj.core.util.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shuxibing
 * @date 2019/11/10 15:01
 * @uint d9lab
 * @Description:
 */
public class ImageDao {

    private Logger logger= LoggerFactory.getLogger(ImageDao.class);
    private Connection conn;
    private Statement st;
    public ImageDao() throws Exception{
        String username="root";
        String password="027D9Lab114215172223";
        String url="jdbc:mysql://115.29.161.187:3306/change";
        String driver="com.mysql.jdbc.Driver";
        //数据库连接
        //第一步：加载驱动
        Class.forName(driver);
        //第二步：建立数据库连接
        Connection conn=DriverManager.getConnection(url,username,password);
        this.conn = conn;
        st= this.conn.createStatement();
    }

    /**
     * 上传图像到fastdfs
     * @param path
     * @throws Exception
     */
    public void insert(String path ) throws Exception{
        java.util.Date date=new java.util.Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(date);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("insert into file(path,create_time,update_time,is_deleted) ")
                    .append("Values(")
                    .append("'"+path+"',")
                    .append("'"+format+"',")
                    .append("'"+format+"',")
                    .append("'"+0+"'")
                    .append(")");
        boolean execute = st.execute(stringBuilder.toString());
        logger.info("插入数据库成功");
    }



    public void search() throws SQLException, IOException {
        String sql="SELECT DISTINCT f.id,f.path,f.create_time,d.detection_photo_index_id FROM file f,detection_photo d WHERE f.id = d.photo  ORDER BY f.create_time DESC";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet= preparedStatement.executeQuery();
        int i=0;
        File file=new File("c:\\Users\\Administrator\\Desktop\\imagefile.txt");
        FileWriter fileWriter=new FileWriter(file);
        int j=0;
        System.out.println(resultSet.getFetchSize());
        while (resultSet.next()){
            j++;
            String path = resultSet.getString("path");
            System.out.println(j+"  "+path);
            String lastName=path.substring(36);
            String MName="";
            char c = path.charAt(34);
            if (c=='A'){
                MName="10";
            }else if (c=='B'){
                MName="11";
            }else{
                MName=""+c;
            }
            System.out.println(c);
            System.out.println(lastName);
            String filePath="/alfa/whut/data"+MName+"/fastdfs/data/"+lastName;
            System.out.println(filePath);
            fileWriter.write(filePath);
            fileWriter.write("\r\n");
            i++;
//            if (i>3000){
//                break;
//            }
        }
        System.out.println("总个数:"+i);
        fileWriter.close();
    }
    public void search(Integer id) throws SQLException, IOException {
//        String sql="SELECT DISTINCT f.id,f.path,f.create_time,d.detection_photo_index_id FROM file f,detection_photo d WHERE f.id = d.photo  ORDER BY f.create_time DESC";
        String sql="SELECT  DISTINCT f.id,d.id as detection_id, f.path,f.create_time,dp.detection_photo_index_id FROM detection d, file f,detection_photo dp WHERE d.id=dp.detection_id and  f.id = dp.photo  ORDER BY f.create_time DESC";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet= preparedStatement.executeQuery();
        int i=0;
        System.out.println(resultSet.getFetchSize());
        while (resultSet.next()){
            String detection_id = resultSet.getString("detection_id");
            String id1 = resultSet.getString("id");
            String path = resultSet.getString("path");
            String create_time = resultSet.getString("create_time");
            String detection_photo_index_id = resultSet.getString("detection_photo_index_id");
            System.out.println(detection_id+" "+id+" "+path+" "+create_time+" "+detection_photo_index_id+" ");
        }
        System.out.println("总个数:"+i);
    }
    public static void main(String[] args) throws Exception {
        ImageDao imageDao=new ImageDao();
        imageDao.search(1);
    }
}
