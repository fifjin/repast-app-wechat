package com.aaa.gj.repast.controller;

import com.aaa.gj.repast.annotation.LoginLogAnnotation;
import com.aaa.gj.repast.base.BaseController;
import com.aaa.gj.repast.base.ResultData;
import com.aaa.gj.repast.model.Member;
import com.aaa.gj.repast.service.IRepastService;
import com.aaa.gj.repast.utils.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-10 14:12
 **/
@RestController
@Api(value = "用户信息",tags = "用户信息接口（提供用户所有操作）")
public class MeberController extends BaseController {
    @Autowired
    private IRepastService repastServicel;
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录",notes = "用户执行登录操作(接收微信端传递的数据)")
    @LoginLogAnnotation(operationType = "登录操作",operationName = "普通用户的登录")
    public ResultData doLogin(Member member){
        Boolean ifsuccess = repastServicel.doLogin(member);
        if (ifsuccess){
            return super.success();
        }
        return super.failed();
    }
  /**
   * @ClassName MeberController
   * @Description : 用户个人信息查询
   *
   * @Return : com.aaa.gj.repast.base.ResultData
   * @Author : gj
   * @Date : 2020/3/13 0:02
  */
  @PostMapping("/selectMember")
  @ApiOperation(value = "查询",notes = "根据openid查询用户信息")
  public ResultData selectMember(@RequestParam(value = "id") String key){
        Member member = repastServicel.selectMember(key);
      String memberJson=JSONUtil.toJsonString(member);
        if (null != member){
            return success(memberJson);
        }else{
            return failed();
        }
  }
}
