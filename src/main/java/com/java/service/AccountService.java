package com.java.service;


import com.xiaowo.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author answer
 *         2017/10/27
 */
public interface AccountService {
    /**
     * 注册账户
     *
     * @param username
     * @param realName
     * @param password
     * @return
     */
    int saveAccount(String username, String realName, String password);

    /**
     * 根据用户名查询账户信息
     *
     * @param username
     * @return
     */
    Account findAccountByName(String username);

    /**
     * 登陆校验
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    String loginCheck(HttpServletRequest request, String username, String password);

    /**
     * 保存信息到session
     *
     * @param request
     * @param object
     * @param type
     */
    void saveInfoToSession(HttpServletRequest request, Object object, String type);

    /**
     * 清空session
     *
     * @param request
     */
    void removeSessionInfo(HttpServletRequest request);

    /**
     * 获取所有的账户信息
     *
     * @return
     */
    List<Account> findAccounts();

    /**
     * 删除账户
     *
     * @param id
     */
    void deleteAccount(Integer id);

    /**
     * 根据id查询账户
     *
     * @param id
     * @return
     */
    Account findAccountById(Integer id);

    /**
     * 修改账户信息
     *
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 修改密码
     *
     * @param accountId
     * @param password
     */
    void updatePassword(Integer accountId, String password);
}
