package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.dao.ProjectDao;

import java.util.Map;

@Component("/project/list.do")
public class ProjectListController implements Controller {

    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {

        model.put("projects", projectDao.selectList());
//        System.out.println(">>>>>>>" + projectDao.selectList().size());
        return "/project/ProjectList.jsp";
    }
}
