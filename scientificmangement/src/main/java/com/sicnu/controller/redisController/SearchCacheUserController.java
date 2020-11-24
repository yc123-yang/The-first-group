package com.sicnu.controller.redisController;

import com.sicnu.service.redisService.redisImpl.SearchCacheUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class SearchCacheUserController {
    @Resource
    SearchCacheUser searchCacheUser;

    @PostMapping("/searchCacheUser")
    public List searchCacheUser() {
        List list = searchCacheUser.searchCacheUser();
        System.out.println(list);
        return list;
    }

}
