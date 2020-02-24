package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.bind.DataBinding;
import com.atoz_develop.spms.dao.ProjectDao;

import java.util.HashMap;
import java.util.Map;

@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding {

    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{"orderCond", String.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderCond", model.get("orderCond"));
        model.put("projects", projectDao.selectList(paramMap));

        return "/project/ProjectList.jsp";
    }
}
