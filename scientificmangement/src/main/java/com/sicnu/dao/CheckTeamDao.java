package com.sicnu.dao;

import com.sicnu.pojo.CheckTeam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface CheckTeamDao {
   void checkProjectTeam(Integer project_id, Integer user_id, Date join_time);
   List<CheckTeam> selectCheckUser(Integer project_id);
   void delCheckTeamUser(Integer user_id);
}
