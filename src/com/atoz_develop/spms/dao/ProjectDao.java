package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.vo.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDao {
    List<Project> selectList() throws SQLException;
    int insert(Project project) throws SQLException;
    Project selectOne(int no) throws SQLException;
    int update(Project project) throws SQLException;
    int delete(int no) throws SQLException;
}
