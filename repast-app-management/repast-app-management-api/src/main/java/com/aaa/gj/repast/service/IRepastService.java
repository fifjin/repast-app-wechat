package com.aaa.gj.repast.service;

import com.aaa.gj.repast.model.LoginLog;
import com.aaa.gj.repast.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "memberinfo-interface")
//@FeignClient(value = "memberinfo-interface", fallbackFactory = RepastFallBackFactory.class)
public interface IRepastService {
    //执行登录操作
    @PostMapping("/doLogin")
    Boolean doLogin(@RequestBody Member member);
    //执行登录日志保存
    @PostMapping("/saveLog")
    Boolean saveLog(@RequestBody LoginLog loginLog);
    //根据openId查询用户信息
    @PostMapping("/selectMember")
    Member selectMember(@RequestParam(value = "id") String key);
}
