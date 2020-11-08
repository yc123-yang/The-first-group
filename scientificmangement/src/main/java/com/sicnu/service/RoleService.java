package com.sicnu.service;

import com.sicnu.util.Result;
import org.springframework.stereotype.Service;


public interface RoleService {
   Result addRole(String role_name);
   Result delRole(Integer role_id);
}
