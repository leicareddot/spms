package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.vo.Project;

import java.util.List;

public interface ProjectDao {
    List<Project> selectList() throws Exception;
}
