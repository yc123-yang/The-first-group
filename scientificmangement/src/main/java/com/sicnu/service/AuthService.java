package com.sicnu.service;

import com.sicnu.pojo.Auth;
import com.sicnu.pojo.Role;
import com.sicnu.util.Result;

import java.util.List;

public interface AuthService {
    Result addAuth(String auth_name);
    Result delAuth(Integer auth_id);
    Result findAllAuth();
    List<Object> getAuth(Integer role_id);
}
