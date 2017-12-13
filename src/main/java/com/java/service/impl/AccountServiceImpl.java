package com.java.service.impl;

import com.java.dao.AccountDao;
import com.java.service.AccountService;
import com.java.service.PermissionService;
import com.java.utils.Constant;
import com.xiaowo.Account;
import com.xiaowo.Menu;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 * @author answer
 *         2017/10/27
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Resource
    private PermissionService permissionService;

    private final static Logger logger = Logger.getLogger(AccountServiceImpl.class);

    @Override
    public int saveAccount(String username, String realName, String password) {
        Account account = new Account();
        account.setUsername(username);
        if (!StringUtils.isEmpty(realName)) {
            account.setRealName(realName);
        }

        //密码加密
        String uuid = UUID.randomUUID().toString();
        String newPassword = encryptPassword(password, uuid);
        account.setUuid(uuid);
        account.setPassword(newPassword);
        return accountDao.insertAccount(account);
    }

    @Override
    public Account findAccountByName(String username) {
        return accountDao.findAccountByUsername(username);
    }

    @Override
    public String loginCheck(HttpServletRequest request, String username, String password) {
        Account account = accountDao.findAccountByUsername(username);
        if (account == null) {
            return Constant.ERRORUSERNAME;
        }
        String status = account.getStatus();
        if ("0".equals(status)) {
            //账户已注销
            return Constant.UNSUBSCRIBE;
        }
        String uuid = account.getUuid();
        String md5Password = account.getPassword();
        String newPassword = encryptPassword(password, uuid);
        if (md5Password.equals(newPassword)) {
            //登陆成功保存信息到session
            saveInfoToSession(request, username, "username");
            //保存当前账户的菜单树
            List<Menu> menuTree = permissionService.findMenuByUserId(account.getId());
            saveInfoToSession(request, menuTree, "menuTree");
            return Constant.LOGINSUCCESS;
        }
        return Constant.ERRORPASSWORD;
    }

    @Override
    public void saveInfoToSession(HttpServletRequest request, Object object, String type) {
        HttpSession session = request.getSession();
        session.setAttribute(type, object);
    }

    @Override
    public void removeSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    @Override
    public List<Account> findAccounts() {
        return accountDao.findAllAccount();
    }

    @Override
    public void deleteAccount(Integer id) {
        accountDao.deleteAccount(id);
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void updatePassword(Integer accountId, String password) {
        Account account = accountDao.findAccountById(accountId);
        String uuid = account.getUuid();
        String newPassword = encryptPassword(password, uuid);
        accountDao.updatePasswordById(newPassword, accountId);
    }

    /**
     * md5加密密码
     *
     * @param password
     * @param uuid
     * @return
     */
    private String encryptPassword(String password, String uuid) {
        String newstr = null;
        String str = password + uuid;
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("加密: " + password + " 后密码：" + newstr);
        return newstr;
    }


}
