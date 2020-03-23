package com.aaa.gj.repast.fallback;

import com.aaa.gj.repast.model.LoginLog;
import com.aaa.gj.repast.model.Member;
import com.aaa.gj.repast.service.IRepastService;
import com.aaa.gj.repast.vo.TokenVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-10 14:31
 **/
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {
    public IRepastService create(Throwable throwable) {
        IRepastService repastService = new IRepastService() {
            public TokenVo doLogin(Member member) {
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

            public Boolean uploadFile(MultipartFile file, String token) {
                System.out.println("熔断文件上传的方法");
                return null;
            }


        };
        return repastService;
    }
}
