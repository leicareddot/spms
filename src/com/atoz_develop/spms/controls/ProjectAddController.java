package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.bind.DataBinding;
import com.atoz_develop.spms.dao.ProjectDao;
import com.atoz_develop.spms.vo.Project;

import java.util.Map;

@Component("/project/add.do")
public class ProjectAddController implements Controller, DataBinding {
    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{"project", Project.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project) model.get("project");

        if (project.getTitle() == null) {   // 폼
            return "/project/ProjectAddForm.jsp";
        } else {                            // 추가
            projectDao.insert(project);
            return "redirect:/project/list.do";
        }
    }
}
