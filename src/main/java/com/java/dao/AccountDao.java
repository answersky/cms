package com.java.dao;

import com.xiaowo.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by answer on 2017/10/27.
 */
public interface AccountDao {
    /**
     * 添加账户
     *
     * @param account
     * @return
     */
    int insertAccount(Account account);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Account findAccountById(@Param("id") Integer id);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    Account findAccountByUsername(@Param("username") String username);

    /**
     * 注销账户
     *
     * @param id
     */
    void unSubscribeById(@Param("id") Integer id);

    /**
     * 修改密码
     *
     * @param password
     * @param id
     */
    void updatePasswordById(@Param("password") String password, @Param("id") Integer id);

    /**
     * 查询所有的账户信息
     *
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 删除账户
     *
     * @param id
     */
    void deleteAccount(Integer id);

    /**
     * 修改账户
     *
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 查询某个角色下绑定的用户
     *
     * @param roleId
     * @return
     */
    List<Account> findBindUserByRoleId(Integer roleId);

    /**
     * 查询未绑定角色的用户
     *
     * @return
     */
    List<Account> findNoBindUser();
}
