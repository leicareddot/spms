package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.vo.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {
    /**
     * 회원 목록 조회
     *
     * @return 회원 목록
     * @throws SQLException
     */
    List<Member> selectList() throws SQLException;

    /**
     * 신규 회원 등록
     *
     * @param member
     * @return
     * @throws SQLException
     */
    int insert(Member member) throws SQLException;

    /**
     * 회원 삭제
     *
     * @param no
     * @return
     * @throws SQLException
     */
    int delete(int no) throws SQLException;

    /**
     * 회원 상세 정보 조회
     *
     * @param no 회원일련번호
     * @return
     * @throws SQLException
     */
    Member selectOne(int no) throws SQLException;

    /**
     * 회원 정보 수정
     *
     * @param member
     * @return
     * @throws SQLException
     */
    int update(Member member) throws SQLException;

    /**
     * 로그인 처리를 위한 Member 리턴
     * @param email
     * @param password
     * @return Member
     * @throws SQLException
     */
    Member exist(String email, String password) throws SQLException;
}
