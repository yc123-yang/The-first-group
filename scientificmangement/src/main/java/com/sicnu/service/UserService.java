package com.sicnu.service;

import com.sicnu.pojo.Permission;
import com.sicnu.pojo.User;
import com.sicnu.util.Result;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public interface UserService {
    Result findByUserAct(String user_act, String user_pwd, HttpSession session);

    Result addUser(String user_act, String user_pwd, String user_name, String user_email, String user_number, String user_id_number, Integer user_state, Integer department_id, Integer role_id) throws MessagingException;

    Result updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number);

    Result changeStatus(Integer user_id, Integer user_state);

    Result loginOut(HttpSession session);

    Result findAllUser();

    // 根据用户名查询该用户的所有权限
    // 根据用户名查询其所有权限
    Set<Permission> queryPermissionByUserId(Integer user_id);

    Set<Permission> queryPermissionByRoleId(Integer role_id);

    Set<Permission> queryAllPermission();

    Result updateUserRole(Integer role_id, Integer user_id);

    Result findUserById(Integer user_id);

    Result findNameId(String user_name);

    Result updateUserMessage(User user);
}
