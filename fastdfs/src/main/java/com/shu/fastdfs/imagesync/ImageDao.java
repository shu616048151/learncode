package com.shu.fastdfs.imagesync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        this.conn = Util.conn();
        st= this.conn.createStatement();
    }
    //将电影信息存入数据库
    public void insert(String title,String year,String country,String lan,String douban_link,Double douban_score,String introduce,String main_actor,String download_url,String img_url ) throws Exception{

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



    public void search() throws SQLException {
        String sql="SELECT DISTINCT f.id,f.path,f.create_time,d.detection_photo_index_id FROM file f,detection_photo d WHERE f.id = d.photo AND detection_photo_index_id = 2 ORDER BY f.create_time DESC";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet= preparedStatement.executeQuery();
        int i=0;
        ExecutorService executorService= Executors.newFixedThreadPool(30);
        while (resultSet.next()){
            System.out.print(resultSet.getString("id")+"   ");
            System.out.print(resultSet.getString("path")+"   ");
            System.out.print(resultSet.getString("create_time")+"   ");
            System.out.println();
            String imageUrl = resultSet.getString("path");
            String savePath=imageUrl.substring(imageUrl.indexOf("group"),imageUrl.lastIndexOf("/"));
            System.out.println(savePath);
            String fileName=imageUrl.substring(imageUrl.lastIndexOf("/")+1);
            System.out.println(fileName);
            DownloadThread downloadThread=new DownloadThread(imageUrl,fileName,"d:\\image\\"+savePath);
            executorService.execute(downloadThread);
            i++;
            if (i>10){
                break;
            }
        }
        System.out.println("总个数:"+i);
    }
    public static void main(String[] args) throws Exception {
        ImageDao imageDao=new ImageDao();
        imageDao.search();
    }
}
