package com.aaa.gj.repast.service;

import com.aaa.gj.repast.base.BaseService;
import com.aaa.gj.repast.mapper.LoginLogMapper;
import com.aaa.gj.repast.model.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-11 23:10
 **/
@Service
public class LoginLogService extends BaseService<LoginLog> {
    @Autowired
    private LoginLogMapper loginLogMapper;
    //保存登录日志的方法
    public Mapper<LoginLog> getMapper() {
        return loginLogMapper;
    }
    public Boolean addLoginLog(LoginLog loginLog){
       try {
           Integer result = super.save(loginLog);
           if (result > 0){
               return true;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return false;
    }
}
