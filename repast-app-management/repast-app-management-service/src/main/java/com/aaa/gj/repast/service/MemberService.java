package com.aaa.gj.repast.service;

import com.aaa.gj.repast.base.BaseService;
import com.aaa.gj.repast.mapper.MemberMapper;
import com.aaa.gj.repast.model.Member;
import com.aaa.gj.repast.utils.IDUtil;
import com.aaa.gj.repast.utils.StringUtil;
import com.aaa.gj.repast.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-10 14:44
 **/
@Service
public class MemberService extends BaseService<Member> {
    @Autowired
    private MemberMapper memberMapper;
    public Mapper<Member> getMapper() {
        return memberMapper;
    }
    public TokenVo doLogin(Member member) {
        TokenVo tokenVo = new TokenVo();
        //判断member是否为null，openid是否为null
        if (null != member && null != member.getOpenId() && StringUtil.isNotEmpty(member.getOpenId())) {
            //判断是否为新用户
            try {
                Member mb = memberMapper.selectMemberByOpenId(member.getOpenId());
                String token = IDUtil.getUUID() + member.getOpenId();
                if (null != mb) {
                    //说明不是新用户，修改token即可
                    mb.setToken(token);
                    Integer updateResult = super.update(mb);
                    if (updateResult > 0) {
                        //修改token成功
                        tokenVo.setToken(token).setIfsuccess(true);
                        return tokenVo;
                    }
                } else {
                    //说明是新用户,需要把用户信息存储到数据库
                    member.setToken(token);
                    Integer saveResult = super.save(member);
                    //判断是否保存成功
                    if (saveResult > 0){
                        tokenVo.setToken(token).setIfsuccess(true);
                        return tokenVo;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tokenVo.setIfsuccess(false);
    }
    /**
     * @ClassName MemberService
     * @Description : 根据openid查询用户信息
     *
     * @param openId
     * @Return :
     * @Author : gj
     * @Date : 2020/3/21 4:01
    */
    public Member selectMember(String openId){
        return memberMapper.selectMemberByOpenId(openId);
    }
}
