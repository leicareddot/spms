package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.bind.DataBinding;
import com.atoz_develop.spms.dao.MemberDao;
import com.atoz_develop.spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/auth/login.do")
public class LogInController implements Controller, DataBinding {
    MemberDao memberDao;

    /**
     * MemberDao를 주입받기 위한 setter()
     * @param memberDao
     * @return LogInController
     */
    public LogInController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    /**
     * 필요한 데이터 정의
     * @return
     */
    @Override
    public Object[] getDataBinders() {
        return new Object[]{"email", String.class, "password", String.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        String email = (String) model.get("email");
        String password = (String) model.get("password");

        if(email == null) {    // Form 조회
            return "/auth/LogInForm.jsp";
        } else {    // 로그인
            Member member = memberDao.exist(email, password);
            if(member == null) {
                return "/auth/LogInFail.jsp";
            } else {
                ((HttpSession) model.get("session")).setAttribute("member", member);

                return "redirect:/member/list.do";
            }
        }
    }
}
