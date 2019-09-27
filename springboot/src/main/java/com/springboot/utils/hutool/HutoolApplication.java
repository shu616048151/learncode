package com.springboot.utils.hutool;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author shuxibing
 * @date 2019/9/24 16:06
 * @uint d9lab
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Controller
public class HutoolApplication {
    public static void main(String[] args){
        SpringApplication.run(HutoolApplication.class,args);
    }

    /**
     * 生成验证码
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getCheckCode")
    public void getCheckCode(HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(300, 100,4,4);
        outputStream.write(circleCaptcha.getImageBytes());
    }

    /**
     * 生成二维码
     * @param response
     * @throws IOException
     */
    @RequestMapping("/qrCode")
    public void getQrCode(HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        QrConfig config = new QrConfig(300, 300);
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色
        config.setForeColor(Color.WHITE.getRGB());
        // 设置背景色
        config.setBackColor(Color.BLACK.getRGB());
        try {
            QrCodeUtil.generate("舒细兵，武汉理工大学",config,"jpg",outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
