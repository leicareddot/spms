package com.atoz_develop.spms.vo;

import java.util.Date;

public class Member {

    private int no;             // 회원 일련번호
    private String email;       // 이메일
    private String password;    // 비밀번호
    private String name;        // 이름
    private Date createdDate;   // 가입일
    private Date modifiedDate;  // 수정일

    public int getNo() {
        return no;
    }

    public Member setNo(int no) {
        this.no = no;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Member setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Member setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Member setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }
}
