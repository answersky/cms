package com.java.interceptor;

import com.java.utils.Properties;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author answer
 * 2017/10/27
 */
public class LoginInterceptor implements HandlerInterceptor {


    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("拦截开始.............");
        HttpSession session = request.getSession();
        Object object = session.getAttribute("username");
        if (object == null) {
            //重定向到登录页面
            response.sendRedirect("/cms/");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 读取本地ip地址文件
     * @param request
     * @return
     */
    private String getIpDBFilePath(HttpServletRequest request) {
        Properties properties = new Properties(null);
        try {
            properties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = "";
        path = properties.getValue("ip_file_path").trim();
        return path;
    }


    /**
     * 获取ip
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
