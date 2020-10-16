package com.sicnu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface Project_TeamDao {
   void addProjectTeam(Integer project_id, Integer user_id, Date join_time);
   void delProjectTeam(Integer project_id);
   void delTeamUser(Integer user_id);
}
