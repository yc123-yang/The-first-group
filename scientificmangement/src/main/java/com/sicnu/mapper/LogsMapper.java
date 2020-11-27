package com.sicnu.mapper;

import com.sicnu.pojo.Slog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LogsMapper {
    List<Slog> findAllLogs(Map<String, Object> map);
    Integer countAllLogs();
    void deleteAllLogs();
}
