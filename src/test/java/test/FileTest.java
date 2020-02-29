package test;

import java.io.*;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shuxibing
 * @date 2019/12/4 9:47
 * @uint d9lab
 * @Description:
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        FileReader fileReader=new FileReader("C:\\Users\\Administrator\\Desktop\\log_V2_80-56.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);


        FileWriter fileWriter=new FileWriter("C:\\Users\\Administrator\\Desktop\\log_1.txt");
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        String line="";
        int i=0;
        double L=0.0;
        double C=0.0;
        String TotelStr="";
        String LStr="";
        String CStr="";
        while ((line=bufferedReader.readLine())!=null){
            System.out.println(line);
            Pattern pattern=Pattern.compile("Totel iter [0-9]+");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()){
                System.out.println("group:"+matcher.group());
                TotelStr = matcher.group();
                TotelStr = TotelStr.substring(11);
            }
            Pattern pattern2=Pattern.compile("L: [0-9]+\\.[0-9]+");
            Matcher matcher2 = pattern2.matcher(line);
            if (matcher2.find()){
                System.out.println("group:"+matcher2.group());
                LStr = matcher2.group();
                String substring = LStr.substring(3);
                L=Double.valueOf(substring);
            }
            Pattern pattern1=Pattern.compile("C: [0-9]+\\.[0-9]+");
            Matcher matcher1 = pattern1.matcher(line);
            if (matcher1.find()){
                System.out.println("group:"+matcher1.group());
                CStr = matcher1.group();
                String substring = CStr.substring(3);
                C=Double.valueOf(substring);
            }
            BigDecimal bigDecimal=new BigDecimal((L+C));
            //保留小数位数
            double value = bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
            String outStr = TotelStr + " " + L + " " + C + " " + " " + value;
            System.out.println(outStr);
            System.out.println(++i);
            bufferedWriter.write(outStr+"\r\n");
        }
        bufferedReader.close();
        bufferedWriter.close();
        fileReader.close();
        fileWriter.close();
    }
}
