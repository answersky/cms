package com.java.dao;

import com.xiaowo.AccountRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author answer
 *         2017/10/30
 */
public interface AccountRoleDao {
    /**
     * 新增用户与角色的关系
     *
     * @param accountRole
     */
    void insertAccountRole(AccountRole accountRole);

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    AccountRole findAccountRoleByUserId(Integer userId);

    /**
     * 删除用户与角色绑定的关系
     *
     * @param roleId
     * @param userId
     */
    void removeAccountRole(@Param("roleId") Integer roleId, @Param("userId") Integer userId);
}
