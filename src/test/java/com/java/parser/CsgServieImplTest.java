package com.java.parser;

import com.java.BaseTest;
import com.utils.ExcelUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author answer
 *         2017/11/20
 */
public class CsgServieImplTest extends BaseTest {
    @Resource
    private CsgServieImpl csgServie;

    @Test
    public void findAddressNumByUsernameAndPhone() throws Exception {
        String filepath = "C:\\Users\\zoe\\Desktop\\ada.xlsx";
        File file = new File(filepath);
        //从excel从读取数据
        List<Map<String, String>> maps = ExcelUtil.resolveExcel(file);
        System.out.println(maps);
        for (Map<String, String> map : maps) {
            String name = map.get("name");
            String phone = map.get("phone");
            String num = csgServie.findAddressNumByUsernameAndPhone(name, phone);
            String info = csgServie.findInfoByAddressNum(num, phone);
            System.out.println("编号：" + num + "  信息：" + info);
        }
    }

}