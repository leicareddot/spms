package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.bind.DataBinding;
import com.atoz_develop.spms.dao.MemberDao;
import com.atoz_develop.spms.vo.Member;

import java.util.Map;

/**
 * 회원 정보 조회 및 수정
 */
@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
    MemberDao memberDao;

    /**
     * MemberDao를 주입받기 위한 setter()
     * @param memberDao
     * @return MemberUpdateController
     */
    public MemberUpdateController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{"member", Member.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");

        if(member.getEmail() == null) {  // 조회
            model.put("member", memberDao.selectOne(member.getNo()));

            return "/member/MemberUpdateForm.jsp";
        } else {    // 수정
            memberDao.update((Member) model.get("member"));

            return "redirect:/member/list.do";
        }
    }
}
