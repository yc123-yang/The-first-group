package com.sicnu.mapper;

import com.sicnu.pojo.Permission;
import com.sicnu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface UserMapper {
    User findByUserAct(String user_act);

    void addUser(User user);

    List<User> findAllUser();

    void updatePwd(String user_act, String user_pwd, String user_email, String user_number, String user_id_number);

    void changeStatus(Integer user_id, Integer user_state);

    User findUserById(Integer user_id);

    int selectUserId(String user_email);

    int findByUserName(String user_name);

    // 根据用户名查询其所有权限
    Set<Permission> queryPermissionByUserId(Integer user_id);

    Set<Permission> queryPermissionByRoleId(Integer role_id);

    Set<Permission> queryAllPermission();

    void updateUserRole(Integer role_id, Integer user_id);
    List<Integer> selectAllUserId();
    List<User> findNameId(String user_name);
}
