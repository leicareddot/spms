package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.vo.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {

    SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 회원 목록 조회
     *
     * @return 회원 목록
     * @throws SQLException
     */
    public List<Member> selectList(Map<String, Object> paramMap) throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            return sqlSession.selectList("com.atoz_develop.spms.dao.MemberDao.selectList", paramMap);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 신규 회원 등록
     *
     * @param member
     * @return
     * @throws SQLException
     */
    public int insert(Member member) throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            int result = sqlSession.insert("com.atoz_develop.spms.dao.MemberDao.insert", member);
            sqlSession.commit();
            return result;
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 회원 삭제
     *
     * @param no
     * @return
     * @throws SQLException
     */
    public int delete(int no) throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            int count = sqlSession.delete("com.atoz_develop.spms.dao.MemberDao.delete", no);
            sqlSession.commit();
            return count;
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 회원 상세 정보 조회
     *
     * @param no 회원일련번호
     * @return
     * @throws SQLException
     */
    public Member selectOne(int no) throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            return sqlSession.selectOne("com.atoz_develop.spms.dao.MemberDao.selectOne", no);
        } finally {
            sqlSession.close();
        }

    }

    /**
     * 회원 정보 수정
     *
     * @param member
     * @return
     * @throws SQLException
     */
    public int update(Member member) throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            Member original = selectOne(member.getNo());
            Map<String, Object> paramMap = new HashMap<>();
            if(!member.getName().equals(original.getName())) paramMap.put("name", member.getName());
            if(!member.getEmail().equals(original.getEmail())) paramMap.put("email", member.getEmail());
            if(!member.getPassword().equals(original.getPassword())) paramMap.put("password", member.getPassword());

            if(paramMap.size() > 0) {
                paramMap.put("no", member.getNo());
                int count = sqlSession.update("com.atoz_develop.spms.dao.MemberDao.update", paramMap);
                sqlSession.commit();
                return count;
            }

            return 0;
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 로그인 처리를 위한 Member 리턴
     * @param email
     * @param password
     * @return Member
     * @throws SQLException
     */
    public Member exist(String email, String password) throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("email", email);
        paramMap.put("password", password);

        try {
            return sqlSession.selectOne("com.atoz_develop.spms.dao.MemberDao.exist", paramMap);
        } finally {
            sqlSession.close();
        }
    }
}