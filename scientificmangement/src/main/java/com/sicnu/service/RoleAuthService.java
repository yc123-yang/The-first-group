package com.sicnu.service;

import com.sicnu.util.Result;

import java.util.List;

public interface RoleAuthService {
    Result updateAuth(Integer role_id, Integer[] authList);
}
