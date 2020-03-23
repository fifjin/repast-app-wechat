package com.aaa.gj.repast.service;

import com.aaa.gj.repast.config.FeignMultiPartConfig;
import com.aaa.gj.repast.model.LoginLog;
import com.aaa.gj.repast.model.Member;
import com.aaa.gj.repast.vo.TokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.aaa.gj.repast.staticstatus.RequesProperties.TOKEN;

@FeignClient(value = "memberinfo-interface",configuration = FeignMultiPartConfig.class)
//@FeignClient(value = "memberinfo-interface", fallbackFactory = RepastFallBackFactory.class)
public interface IRepastService {
    //执行登录操作
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody Member member);
    //执行登录日志保存
    @PostMapping("/saveLog")
    Boolean saveLog(@RequestBody LoginLog loginLog);
    //根据openId查询用户信息
    @PostMapping("/selectMember")
    Member selectMember(@RequestParam(value = "id") String key);
    //ftp文件的上传
    @PostMapping(value = "upload",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file,@RequestParam(TOKEN)String token);
}
