package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.vo.Project;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;
import java.util.Hashtable;
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
            // DB에서 기존 프로젝트 정보 가져옴
            Project original = sqlSession.selectOne("com.atoz_develop.spms.dao.ProjectDao.selectOne", project.getNo());
            Map<String, Object> paramMap = new Hashtable<>();
            // 요청 파라미터로 넘어온 프로젝트와 비교해서 다르면 map에 담는다.
            if (!project.getTitle().equals(original.getTitle())) {
                paramMap.put("title", project.getTitle());
            }
            if (!project.getContent().equals(original.getContent())) {
                paramMap.put("content", project.getContent());
            }
            if (!project.getStartDate().equals(original.getStartDate())) {
                paramMap.put("startDate", project.getStartDate());
            }
            if (!project.getEndDate().equals(original.getEndDate())) {
                paramMap.put("endDate", project.getEndDate());
            }
            if (!(project.getState() == original.getState())) {
                paramMap.put("state", project.getState());
            }
            if (!project.getTags().equals(original.getTags())) {
                paramMap.put("tags", project.getTags());
            }

            // 변경된 값이 있으면 update 실행
            if(paramMap.size() > 0) {
                paramMap.put("no", project.getNo());
                int count = sqlSession.update("com.atoz_develop.spms.dao.ProjectDao.update", paramMap);
                sqlSession.commit();
                return count;
            }

            return 0;
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
