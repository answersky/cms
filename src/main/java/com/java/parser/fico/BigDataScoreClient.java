package com.java.parser.fico;

import com.java.utils.Properties;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.dom4j.*;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BigDataScoreClient {

    //production load balance
    //	private static final String URL = "https://123.206.4.118:8000/BigDataScore/GetScore";

    public static void main(String[] args) throws Exception {


        BigDataScoreClient client = new BigDataScoreClient();

        String inputXMLPart1 = "<Request clientID=\"Xiaowo-ICBC\" pboc=\"false\" mobHeader=\"135\" password=\"TEST\" serviceCode=\"101\" idCard=\"0ff649c2605c871149b10eb13751b151\" mobile=\"";

        String inputXMLPart2 = "\" />";
        String mobile = null;

        String result = null, retcode = null;
        CloseableHttpClient httpClient = BigDataScoreClient.getHttpClient();

        mobile = getMD5("18576935720");

        String xml = inputXMLPart1 + mobile + inputXMLPart2;

        try {

            while (true) {
                result = client.execute(httpClient, xml);
                int index = result.indexOf("retCode=\"") + "retCode=\"".length();
                retcode = result.substring(index, index + 3);

                if ("909".equals(retcode)) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result);
        client.String2Xml(result);
    }


    private static final String CHARSET = "UTF-8";
    private static final String CONTENT_TYPE_TEXT = "text/plain";

    private HttpPost post;


    public List<Map<String, String>> analysis(String idCard, String tel) {
        List<Map<String, String>> results = new ArrayList<>();
        //读取配置
        Properties properties = new Properties(null);
        try {
            properties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String clientID = properties.getValue("fico.clientID");
        String pboc = properties.getValue("fico.pboc");
        String password = properties.getValue("fico.password");
        String serviceCode = properties.getValue("fico.serviceCode");
        BigDataScoreClient client = new BigDataScoreClient();
        String mobHeader = tel.substring(0, 3);
        String telMd5 = getMD5(tel);
        String idCardMd5 = getMD5(idCard);
        String xml = "<Request clientID=\"" + clientID + "\" pboc=\"" + pboc + "\" mobHeader=\"" + mobHeader + "\" password=\"" + password + "\" serviceCode=\"" + serviceCode + "\" idCard=\"" + idCardMd5 + "\" mobile=\"" + telMd5 + "\" />";

        String result = null, retcode = null;
        try {
            CloseableHttpClient httpClient = BigDataScoreClient.getHttpClient();
            while (true) {
                result = client.execute(httpClient, xml);
                int index = result.indexOf("retCode=\"") + "retCode=\"".length();
                retcode = result.substring(index, index + 3);

                if ("909".equals(retcode)) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result);
        results = client.String2Xml(result);
        return results;
    }


    public static CloseableHttpClient getHttpClient() throws Exception {

        SSLContext sslContext = SSLContexts.custom()
                .useTLS()
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                .build();

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);


        Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .build();

        //Set time out
        SocketConfig socketConfig = SocketConfig.custom()
//                .setSoTimeout(5000)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(r);

        cm.setDefaultSocketConfig(socketConfig);

        cm.setMaxTotal(10);
        cm.setDefaultMaxPerRoute(10);

        //keep alive
        ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {

            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);

                if (keepAlive == -1) {
                    keepAlive = 10000;
                }
                return keepAlive;
            }

        };

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
//	            .setKeepAliveStrategy(keepAliveStrat)
                .build();

        //close Expired and Idle Connections
        IdleConnectionMonitorRunnable1 monitor = new IdleConnectionMonitorRunnable1(cm);
        Thread mt = new Thread(monitor);
//	    mt.start();

        return httpClient;
    }

    public String execute(CloseableHttpClient httpClient, String reqXML) throws Exception {
        HttpResponse res = null;
        String result = null;
        //读取配置
        Properties properties = new Properties(null);
        try {
            properties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getValue("fico.url");
        post = new HttpPost(url);
        StringEntity se = new StringEntity(reqXML, CHARSET);
        se.setContentType(CONTENT_TYPE_TEXT);
        se.setContentEncoding(CHARSET);
        post.setEntity(se);

        res = httpClient.execute(post);

        if (res.getStatusLine().getStatusCode() == 200) {

            //process result
            HttpEntity entity = res.getEntity();
            result = EntityUtils.toString(entity);
            if (entity != null) {
                InputStream instream = entity.getContent();
                instream.close();
            }
        } else {
            result = "HTTP returns: " + res.getStatusLine().getStatusCode() + res.getStatusLine().getReasonPhrase();
        }

        return result;
    }


    public void abort() {
        this.post.abort();
    }


    public static String getMD5(String inputString) {

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(inputString.getBytes("UTF-8"));
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;

        } catch (Exception e) {
            return null;
        }
    }

    private List<Map<String, String>> String2Xml(String string) {
        List<Map<String, String>> results = new ArrayList<>();
        try {
            Document document = DocumentHelper.parseText(string);
            //获取根节点元素对象
            Element root = document.getRootElement();
            List<Attribute> list = root.attributes();
            //遍历属性节点
            for (Attribute attribute : list) {
                String attrName = attribute.getName();
                String attrValue = attribute.getValue();
                if ("retCode".equals(attrName)) {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("type", attrName);
                    map.put("value", attrValue);
                    String info = FicoResultAnalysis.analysis(attrValue);
                    map.put("info", info);
                    results.add(map);
                }
                if ("score".equals(attrName)) {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("type", attrName);
                    map.put("value", attrValue);
                    map.put("info", "");
                    results.add(map);
                }
                if ("recAction".equals(attrName)) {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("type", attrName);
                    map.put("value", attrValue);
                    map.put("info", "");
                    results.add(map);
                }
                if ("reason".equals(attrName)) {
                    String[] reasons = {};
                    if (attrValue.contains(",")) {
                        reasons = attrValue.split(",");
                    } else {
                        reasons[0] = attrValue;
                    }
                    for (String code : reasons) {
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("type", attrName);
                        map.put("value", code);
                        String result = FicoResultAnalysis.analysis(code);
                        map.put("info", result);
                        results.add(map);
                    }

                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return results;
    }

}


class IdleConnectionMonitorRunnable1 implements Runnable {

    private final PoolingHttpClientConnectionManager connMgr;
    private volatile boolean shutdown;

    public IdleConnectionMonitorRunnable1(PoolingHttpClientConnectionManager connMgr) {
        super();
        this.connMgr = connMgr;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // Close expired connections
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // terminate
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
