package com.java.service;

import com.java.BaseTest;
import com.xiaowo.Menu;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */
public class MenuServiceTest extends BaseTest {
    @Resource
    private MenuService menuService;

    @Test
    public void menuTree() throws Exception {
        List<Menu> list = menuService.menuTree();
        System.out.println(list);
    }

}