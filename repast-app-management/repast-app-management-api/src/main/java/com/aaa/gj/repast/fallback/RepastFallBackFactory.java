package com.aaa.gj.repast.fallback;

import com.aaa.gj.repast.model.LoginLog;
import com.aaa.gj.repast.model.Member;
import com.aaa.gj.repast.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-10 14:31
 **/
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {
    public IRepastService create(Throwable throwable) {
        IRepastService repastService = new IRepastService() {
            public Boolean doLogin(Member member) {
                System.out.println("熔断登录方法！");
                return null;
            }

            public Boolean saveLog(LoginLog loginLog) {
                System.out.println("熔断记录日志方法");
                return null;
            }

            public Member selectMember(String key) {
                System.out.println("熔断查询用户信息的方法");
                return null;
            }


        };
        return repastService;
    }
}
