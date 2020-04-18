package com.springboot.base.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: UserRoleUtil
 * @Desctiption:
 * @Author: XWY
 * @Date: 2019/7/26 8:59
 * @Version: 1.0
 */
public class UserRoleUtil {

    //划分地址的区域
    public static String  getInstructorRegion(String addressDetail,int level) throws Exception {
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher matcher = Pattern.compile(regex).matcher(addressDetail);
        String province = null, city = null, county = null;
        while (matcher.find()) {
            province = matcher.group("province");
            city = matcher.group("city");
            county = matcher.group("county");
        }
        if(level==1) return city;
        else if(level==2) return county;
        else return province;
    }
    public static void main(String[] args) throws Exception {
        String area = UserRoleUtil.getInstructorRegion("湖北省黄石市大冶市", 1);
        System.out.println(area);
    }
}
