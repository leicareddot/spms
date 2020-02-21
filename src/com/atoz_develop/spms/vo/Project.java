package com.atoz_develop.spms.vo;

import java.util.Date;

public class Project {
    private int no;             // 프로젝트 일련번호
    private String title;       // 프로젝트명
    private String content;     // 설명
    private Date startDate;     // 시작일
    private Date endDate;       // 종료일
    private int state;          // 상태
    private Date createdDate;   // 생성일
    private String tags;        // 태그

    public int getNo() {
        return no;
    }

    public Project setNo(int no) {
        this.no = no;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Project setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Project setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Project setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public int getState() {
        return state;
    }

    public Project setState(int state) {
        this.state = state;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Project setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Project setTags(String tags) {
        this.tags = tags;
        return this;
    }
}
