package com.sicnu.mapper;

import com.sicnu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User findByName(String user_act);
    void addUser(User user);
    List<User> findAll();
    void updatePwd(String user_act,String user_pwd,String user_email,String user_number,String user_id_number);
    void changeStatus(Integer user_id,Integer user_state);
    User findUserById(Integer user_id);
    int selectUserId(String user_email);
}
