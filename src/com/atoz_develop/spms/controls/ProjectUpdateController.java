package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.bind.DataBinding;
import com.atoz_develop.spms.dao.ProjectDao;
import com.atoz_develop.spms.vo.Project;

import java.util.Map;

@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {

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

        if(project.getTitle() == null) {    // 정보 조회
            model.put("project", projectDao.selectOne(project.getNo()));
            return "/project/ProjectUpdateForm.jsp";
        } else {    // 변경
            projectDao.update(project);
            return "redirect:/project/list.do";
        }
    }
}
