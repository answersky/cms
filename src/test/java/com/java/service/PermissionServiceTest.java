package com.java.service;

import com.java.BaseTest;
import com.xiaowo.Menu;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/10/30
 */
public class PermissionServiceTest extends BaseTest {
    @Resource
    private PermissionService permissionService;

    @Test
    public void findMenuByUserId() throws Exception {
        List<Menu> menuTree = permissionService.findMenuByUserId(5);
        System.out.println(menuTree);
    }

}