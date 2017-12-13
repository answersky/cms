package com.java.parser;

import com.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author answer
 *         2017/11/20
 */
@Service
public class CsgServieImpl {
    /**
     * 南方电网深圳查询地址
     */
    private static final String addressNumUrl = "https://95598.sz.csg.cn/kh/likeYhbh.do?action=query";
    /**
     * 根据用户的房屋编号查询用户信息
     */
    private static final String selectUserInfoUrl = "https://95598.sz.csg.cn/df/wsjfsz.do?action=yhqfcx";

    /**
     * 查询房屋编号
     *
     * @param username
     * @param phone
     * @return
     */
    public String findAddressNumByUsernameAndPhone(String username, String phone) {
        Map<String, String> param = new LinkedHashMap<>();
        String url = addressNumUrl + "&yhmc=" + username + "&sjhm=" + phone;
        String html = HttpClientUtil.httpsRequest(param, url);
        String addressNum = Csgparser.findAddressNum(html);
        return addressNum;
    }

    /**
     * 查询用户信息
     *
     * @param addressNum
     * @param phone
     * @return
     */
    public String findInfoByAddressNum(String addressNum, String phone) {
        Map<String, String> param = new LinkedHashMap<>();
        String url = selectUserInfoUrl + "&yhbh=" + addressNum + "&sjh=" + phone;
        String html = HttpClientUtil.httpsRequest(param, url);
        String info = Csgparser.analysisHtml(html);
        return info;
    }
}
