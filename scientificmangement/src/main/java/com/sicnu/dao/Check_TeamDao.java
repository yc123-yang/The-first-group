package com.sicnu.dao;

import com.sicnu.pojo.Check_Team;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface Check_TeamDao {
   void checkProjectTeam(Integer project_id, Integer user_id, Date join_time);
   List<Check_Team> selectCheckUser(Integer project_id);
   void delCheckTeamUser(Integer user_id);
}
