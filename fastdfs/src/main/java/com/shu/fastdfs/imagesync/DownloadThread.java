package com.shu.fastdfs.imagesync;

/**
 * @author shuxibing
 * @date 2019/11/11 15:16
 * @uint d9lab
 * @Description:
 */
public class DownloadThread implements  Runnable {
    private String url;
    private String filename;
    private String savePath;

    public DownloadThread(String url, String filename, String savePath) {
        this.url = url;
        this.filename = filename;
        this.savePath = savePath;
    }
    @Override
    public void run() {
        try {
            System.out.println("开始下载："+url);
            DownloadImage.download(url,filename,savePath);
            System.out.println("下载完成:"+url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("问题url:"+url+"下载出问题图地址:"+savePath+filename);
        }
    }
}
