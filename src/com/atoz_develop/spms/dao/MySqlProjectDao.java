package com.atoz_develop.spms.dao;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.vo.Project;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Project> selectList() throws SQLException {
        List<Project> projects = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(
                    "SELECT PNO, PNAME, STA_DATE, END_DATE, STATE, CRE_DATE" +
                            " FROM PROJECTS" +
                            " ORDER BY CRE_DATE DESC"
            );
            rs = pstmt.executeQuery();

            while (rs.next()) {
                projects.add(new Project()
                        .setNo(rs.getInt("PNO"))
                        .setTitle(rs.getString("PNAME"))
                        .setStartDate(rs.getDate("STA_DATE"))
                        .setEndDate(rs.getDate("END_DATE"))
                        .setState(rs.getInt("STATE"))
                        .setCreatedDate(rs.getDate("CRE_DATE"))
                );
            }

            return projects;

        } catch (SQLException e) {
            throw e;

        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    @Override
    public int insert(Project project) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(
                "INSERT INTO PROJECTS(PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS)" +
                        " VALUES(?, ?, ?, ?, 0, NOW(), ?)"
            );
            pstmt.setString(1, project.getTitle());
            pstmt.setString(2, project.getContent());
            pstmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            pstmt.setString(5, project.getTags());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;

        } finally {
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
        }
    }

    @Override
    public Project selectOne(int no) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(
                    "SELECT PNO, PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS" +
                            " FROM PROJECTS" +
                            " WHERE PNO = " + no
            );
            rs = pstmt.executeQuery();

            if(rs.next()) {
                return new Project()
                        .setNo(rs.getInt("PNO"))
                        .setTitle(rs.getString("PNAME"))
                        .setContent(rs.getString("CONTENT"))
                        .setStartDate(rs.getDate("STA_DATE"))
                        .setEndDate(rs.getDate("END_DATE"))
                        .setState(rs.getInt("STATE"))
                        .setCreatedDate(rs.getDate("CRE_DATE"))
                        .setTags(rs.getString("TAGS"));
            } else {
                throw new SQLException("해당 번호의 프로젝트를 찾을 수 없습니다.");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    @Override
    public int update(Project project) throws SQLException {
        return 0;
    }
}
