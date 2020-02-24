package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.vo.Project;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {

    // myBatis - SqlSessionFactory: SqlSession 객체 생성
    private SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Project> selectList(Map<String, Object> paramMap) throws SQLException {

        // myBatis - SqlSession: SQL 실행
        // openSession(): SqlSession 얻기
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // selectList(): SELECT
            // 파라미터: SQL 맵퍼의 네임 스페이스(com.atoz_develop.spms.dao.ProjectDao) + SQL문 ID(selectList)
            return sqlSession.selectList("com.atoz_develop.spms.dao.ProjectDao.selectList", paramMap);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public int insert(Project project) throws SQLException {
    //        SqlSession sqlSession = sqlSessionFactory.openSession(true);  // auto commit
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // insert(): INSERT
            int count = sqlSession.insert("com.atoz_develop.spms.dao.ProjectDao.insert", project);
            sqlSession.commit();

            return count;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Project selectOne(int no) throws SQLException {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            return sqlSession.selectOne("com.atoz_develop.spms.dao.ProjectDao.selectOne", no);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public int update(Project project) throws SQLException {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int count = sqlSession.update("com.atoz_develop.spms.dao.ProjectDao.update", project);
            sqlSession.commit();
            return count;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public int delete(int no) throws SQLException {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int count = sqlSession.delete("com.atoz_develop.spms.dao.ProjectDao.delete", no);
            sqlSession.commit();
            return count;
        } finally {
            sqlSession.close();
        }
    }
}
