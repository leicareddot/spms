package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.bind.DataBinding;
import com.atoz_develop.spms.dao.MemberDao;

import java.util.Map;

/**
 * 회원 삭제
 */
@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
    MemberDao memberDao;

    /**
     * MemberDao를 주입받기 위한 setter()
     * @param memberDao
     * @return MemberDeleteController
     */
    public MemberDeleteController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{"no", Integer.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        memberDao.delete((int) model.get("no"));

        return "redirect:/member/list.do";
    }
}
