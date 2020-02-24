package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.vo.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ProjectDao {
    List<Project> selectList(Map<String, Object> paramMap) throws SQLException;
    int insert(Project project) throws SQLException;
    Project selectOne(int no) throws SQLException;
    int update(Project project) throws SQLException;
    int delete(int no) throws SQLException;
}
