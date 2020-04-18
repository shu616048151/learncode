package com.shu.springmvc.redirect;

import io.swagger.annotations.ApiOperation;
import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * edu.whut.change.common.com.shu.springframework.controller
 * 这是一个后端进行多项目跳转的类
 */
@ApiIgnore
@Controller
@RequestMapping(value = "/user/redirect")
public class RedirectController {

    //在user项目中关于其它子项目比如trade项目的jsp页面
    //ajax请求需要加上前缀/user/redirect
    private static int offset = "/user/redirect/".length();
    private static Logger logger = Logger.getLogger(RedirectController.class);

    @ApiOperation(value = "重定向", hidden = true)
    @RequestMapping(value = "/**", method = {RequestMethod.POST, RequestMethod.GET})
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getRequestURI();
        int base = request.getContextPath().length();
        String host = url.substring(base + offset, url.indexOf("/",base + offset));//例如: trade
        logger.info("request.getRequestURI():"+request.getRequestURI()+"\tbase:"+base+"\thost:"+host);
        //host url request response additionalMap
        HttpUtil.httpRequest(host, url.substring(url.indexOf("/",base + offset)),//例如: /allProduction/getAllProductionPage
                request, response, new ArrayList<NameValuePair>());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}
