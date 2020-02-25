package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.vo.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {

    SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    DataSource ds;

    public void setDs(DataSource ds) {
        this.ds = ds;
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
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ds.getConnection();
            pstmt = connection.prepareStatement(
                    "INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)" +
                            " VALUES (?, ?, ?, NOW(), NOW())"
            );

            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) { }
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
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = ds.getConnection();
            pstmt = connection.prepareStatement(
                    "DELETE FROM MEMBERS WHERE MNO = ?"
            );
            pstmt.setInt(1, no);

            return pstmt.executeUpdate();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
            }
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
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(
                    "SELECT MNO, EMAIL, MNAME, CRE_DATE, MOD_DATE" +
                            " FROM MEMBERS" +
                            " WHERE MNO = " + no
            );

            if(rs.next()) {
                return new Member()
                        .setNo(no)
                        .setEmail(rs.getString("EMAIL"))
                        .setName(rs.getString("MNAME"))
                        .setCreatedDate(rs.getDate("CRE_DATE"))
                        .setModifiedDate(rs.getDate("MOD_DATE"));
            } else {
                throw new SQLException("해당 번호의 회원을 찾을 수 없습니다.");
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ds.getConnection();
            pstmt = connection.prepareStatement(
                    "UPDATE MEMBERS SET MNAME = ?, EMAIL = ?, MOD_DATE = NOW() WHERE MNO = ?"
            );
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setInt(3, member.getNo());
            return pstmt.executeUpdate();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
            }
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
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // DB에서 회원 정보 조회
            connection = ds.getConnection();
            pstmt = connection.prepareStatement(
                    "SELECT MNAME FROM MEMBERS" +
                            " WHERE EMAIL = ? AND PWD = ?"
            );
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            // 일치하는 회원 있으면
            if (rs.next()) {
                return new Member()
                        .setName(rs.getString("MNAME"))
                        .setEmail(email);
            } else return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) { }
        }
    }
}