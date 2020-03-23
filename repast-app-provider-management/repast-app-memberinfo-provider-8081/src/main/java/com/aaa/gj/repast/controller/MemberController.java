package com.aaa.gj.repast.controller;

import com.aaa.gj.repast.model.LoginLog;
import com.aaa.gj.repast.model.Member;
import com.aaa.gj.repast.service.LoginLogService;
import com.aaa.gj.repast.service.MemberService;
import com.aaa.gj.repast.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-10 14:41
 **/
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private LoginLogService loginLogService;
    //执行登录的方法
    @PostMapping("/doLogin")
    public TokenVo doLogin(@RequestBody Member member){
        return memberService.doLogin(member);
    }
    //执行登录日志保存
    @PostMapping("/saveLog")
    public Boolean saveLog(@RequestBody LoginLog loginLog){
        return loginLogService.addLoginLog(loginLog);
    }
    //根据openId查询用户信息
    @PostMapping("/selectMember")
    public Member selectMember(@RequestParam(value = "id") String key){
        return memberService.selectMember(key);
    }
}
