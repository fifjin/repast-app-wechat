package com.aaa.gj.repast.mapper;

import com.aaa.gj.repast.model.Member;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {

    Member selectMemberByOpenId(String openid);
}