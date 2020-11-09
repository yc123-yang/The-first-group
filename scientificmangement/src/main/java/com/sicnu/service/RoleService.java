package com.sicnu.service;

import com.sicnu.pojo.Role;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {
   Result addRole(String role_name);
   Result delRole(Integer role_id);
   Result findAllRole();
}
