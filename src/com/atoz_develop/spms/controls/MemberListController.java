package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.dao.MemberDao;

import java.util.Map;

/**
 * 회원 목록 조회
 */
@Component("/member/list.do")
public class MemberListController implements Controller {
    MemberDao memberDao;

    /**
     * MemberDao를 주입받기 위한 setter()
     * @param memberDao
     * @return MemberListController
     */
    public MemberListController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        model.put("members", memberDao.selectList());

        return "/member/MemberList.jsp";
    }
}
