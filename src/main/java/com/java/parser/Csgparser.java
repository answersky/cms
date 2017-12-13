package com.java.parser;

import com.utils.HttpClientUtil;
import com.utils.JsonUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author answer
 *         2017/11/13
 *         南方电网解析
 */
public class Csgparser {
    public static void main(String[] args) {
        Map<String, String> param = new LinkedHashMap<>();
//        String url = "https://95598.sz.csg.cn/df/wsjfsz.do?action=yhqfcx&yhbh=0947160005510216&sjh=18576435724&sjyzm=4831";
        String url = "https://95598.sz.csg.cn/kh/likeYhbh.do?action=query&yhmc=杜胜坚&sjhm=13923647328&yzm=9943";
//        param.put("action","yhqfcx");
//        param.put("yhbh","0947160005510216");
//        param.put("sjh","18576435724");
//        param.put("sjyzm","4831");
        String html = HttpClientUtil.httpsRequest(param, url);
//        analysisHtml(html);
        findAddressNum(html);
    }

    public static String analysisHtml(String html) {
        Map<String, String> infoMap = new LinkedHashMap<>();
        if (!StringUtils.isEmpty(html)) {
            Document doc = Jsoup.parse(html);
            Element usernameEle = doc.select("#username").first();
            if (usernameEle != null) {
                //用户名
                String username = usernameEle.text().trim();
//                System.out.println("姓名:" + username);
                infoMap.put("姓名", username);
            }

            Element addressEle = doc.select("#address").first();
            if (addressEle != null) {
                //用电地址
                String address = addressEle.text().trim();
//                System.out.println("用电地址：" + address);
                infoMap.put("用电地址", address);
            }

            Element bankEle = doc.select("#bankname").first();
            if (bankEle != null) {
                //开户行
                String bank = bankEle.text().trim();
//                System.out.println("开户行：" + bank);
                infoMap.put("开户行", bank);
            }

            Element bankNumEle = doc.select("#banknumber").first();
            if (bankNumEle != null) {
                //银行卡号
                String bankNum = bankNumEle.text().trim();
//                System.out.println("银行卡号：" + bankNum);
                infoMap.put("银行卡号", bankNum);
            }

        }
        return JsonUtils.objectToJson(infoMap);
    }

    public static String findAddressNum(String html) {
        String num = "";
        if (!StringUtils.isEmpty(html)) {
            Document doc = Jsoup.parse(html);
            num = doc.select("#form select option").val().trim();
            System.out.println("房屋编号：" + num);
        }
        return num;
    }

}
