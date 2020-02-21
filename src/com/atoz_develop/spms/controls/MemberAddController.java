package com.atoz_develop.spms.controls;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.bind.DataBinding;
import com.atoz_develop.spms.dao.MemberDao;
import com.atoz_develop.spms.vo.Member;

import java.util.Map;

/**
 * 신규 회원 등록
 */
@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {
    MemberDao memberDao;

    /**
     * MemberDao를 주입받기 위한 setter()
     * @param memberDao
     * @return MemberAddController
     */
    public MemberAddController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    /**
     * 필요한 데이터 정의
     * @return 데이터의 이름과 타입 정보를 담은 Object[]
     */
    @Override
    public Object[] getDataBinders() {
        return new Object[]{"member", Member.class};
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");

        if(member.getEmail() == null) {      // Form 요청 시
            return "/member/MemberAddForm.jsp";
        } else {                                // 회원 등록 요청 시
            memberDao.insert(member);
            return "redirect:/member/list.do";
        }
    }
}
