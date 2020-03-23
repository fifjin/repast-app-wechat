package com.aaa.gj.repast.status;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-09 18:18
 **/
public enum  LoginStatus {
    LOGIN_SUCCESS("201", "登陆成功"),
    LOGIN_FAILED("501", "登陆失败"),
    USER_EXIST("202", "用户存在"),
    USER_NOT_EXIST("401", "用户不存在"),
    PASSWORD_WRONG("502", "密码错误"),
    LOGOUT_WRONG("503", "用户退出异常"),
    SUCCESS("203","操作成功"),
    FAILED("403","操作失败");
    LoginStatus(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
