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
}
