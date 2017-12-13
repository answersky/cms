package com.java.service;


import com.xiaowo.Account;
import com.xiaowo.AccountRole;

import java.util.List;

/**
 * @author answer
 *         2017/10/31
 */
public interface UserRoleService {

    /**
     * 查询用户绑定的角色
     *
     * @param userId
     * @return
     */
    AccountRole findAccountRoleByUserId(Integer userId);

    /**
     * 绑定角色
     *
     * @param userId
     * @param roleId
     */
    void bindRole(Integer userId, Integer roleId);

    /**
     * 解绑角色
     *
     * @param userId
     * @param roleId
     */
    void unbindRole(Integer userId, Integer roleId);

    /**
     * 查询当前角色下绑定的所有用户
     *
     * @param roleId
     * @return
     */
    List<Account> findBindUserByRoleId(Integer roleId);

    /**
     * 查询未绑定角色的所有用户
     *
     * @return
     */
    List<Account> findUnBindUser();
}
