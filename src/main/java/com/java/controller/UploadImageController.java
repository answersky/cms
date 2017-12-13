package com.java.controller;

import com.google.gson.JsonObject;
import com.java.service.PicService;
import com.java.utils.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author answer
 *         2017/11/2
 */
@Controller
@RequestMapping("upload")
public class UploadImageController {
    @Resource
    private PicService picService;

    /**
     * 上传图片
     * @param upfile
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/images")
    public Map<String, Object> images (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        String imgHost = getFilePath("img_host");
        if (!upfile.isEmpty()) {
            FileOutputStream fos = null;
            try {
                String path = getFilePath("img_upload_path");
                String fName = "";
                String orgiginalFileName = upfile.getOriginalFilename();
                if (orgiginalFileName.contains(".")) {
                    int indexdot = orgiginalFileName.indexOf(".");
                    String suffix = orgiginalFileName.substring(indexdot);
                    String uuid = UUID.randomUUID().toString();
                    fName = uuid + suffix;
                }
                byte[] bytes = upfile.getBytes();
                fos = new FileOutputStream(path + fName);
                fos.write(bytes);
                params.put("state", "SUCCESS");
                params.put("url", imgHost + fName);
                params.put("size", upfile.getSize());
                params.put("original", upfile.getOriginalFilename());
                params.put("type", upfile.getContentType());

                //保存图片
                picService.savePicture(imgHost + fName);

            } catch (IOException e) {
                e.printStackTrace();
                params.put("state", "ERROR");
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return params;
    }

    @RequestMapping("/picUpload")
    @ResponseBody
    public String picUpload(@RequestParam MultipartFile file) {
        JsonObject jsonObject = new JsonObject();
        String imgHost = getFilePath("img_host");
        String fName = fileName(file);
        try {
            String path = getFilePath("img_upload_path");
            File newFile = new File(path + fName);
            file.transferTo(newFile);

            //保存到数据库
            Integer picId = picService.savePicture(imgHost + fName);
            jsonObject.addProperty("picUrl", imgHost + fName);
            jsonObject.addProperty("picId", picId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private String fileName(MultipartFile file) {
        String fName = "";
        String orgiginalFileName = file.getOriginalFilename();
        if (orgiginalFileName.contains(".")) {
            int indexdot = orgiginalFileName.indexOf(".");
            String suffix = orgiginalFileName.substring(indexdot);
            String uuid = UUID.randomUUID().toString();
            fName = uuid + suffix;
            System.out.println("fileName:" + fName);
        }
        return fName;
    }

    private String getFilePath(String key) {
        Properties properties = new Properties();
        try {
            properties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getValue(key);
    }

}
