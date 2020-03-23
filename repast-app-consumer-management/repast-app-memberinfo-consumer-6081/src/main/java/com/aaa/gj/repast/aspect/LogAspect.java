package com.aaa.gj.repast.aspect;

import com.aaa.gj.repast.annotation.LoginLogAnnotation;
import com.aaa.gj.repast.model.LoginLog;
import com.aaa.gj.repast.model.Member;
import com.aaa.gj.repast.service.IRepastService;
import com.aaa.gj.repast.utils.AddressUtil;
import com.aaa.gj.repast.utils.DateUtil;
import com.aaa.gj.repast.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static com.aaa.gj.repast.staticstatus.StaticCode.*;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-11 21:09
 **/
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private IRepastService repastService;
    //就是为了定义切面，也就是说让AOP在哪里生效
    //     *      也就是说当AOP检测到LoginLogAnnotation注解的时候，被该注解所标识的方法就会执行
    //     *      切面业务代码
    @Pointcut("@annotation(com.aaa.gj.repast.annotation.LoginLogAnnotation)")
    public void pointcut(){}
    // 定义环形切面(具体要执行业务逻辑的代码)
    //     *      ProceedingJoinPoint:封装了目标路径(被LoginLogAnnotation注解所标识方法)
    //     *      中的所有参数
    //     *      也就是说我可以通过这个ProceedingJoinPoint参数获取(获取目标路径的方法名，方法参数个数，方法参数类型，方法
    //     *      返回值，方法参数的值)
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
        Object result = null;
        try{
            result = proceedingJoinPoint.proceed();
        }catch (Throwable t){
            t.printStackTrace();
        }
        //获取Request对象，用于获取用户ip
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //使用IDUtil来获取用户ip地址
        String ipAddr = IPUtil.getIpAddr(request);
        //获取MemberController中doLongin方法中的Member参数对象(args里是参数member的属性)
        Object[] args = proceedingJoinPoint.getArgs();
        Member member =null;
        for(Object arg : args){
            member = (Member)arg;
        }
        //获取operationType和operationName的值
        //1.获取目标路径（只能是类-->MemberController）的全限定名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //2.通过反射获取类对象（class）
        Class targetClass = Class.forName(className);
        //3获取所需要切的具体方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //4.MemberController中所有的方法
        Method[] methods = targetClass.getMethods();
        String operationType = "";
        String operationName = "";
        //5.判断方法名和方法的参数类型个数是否一致（考虑到方法的重载）
        for(Method method : methods){
            if (method.getName().equals(methodName)){
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == args.length){
                    operationType = method.getAnnotation(LoginLogAnnotation.class).operationType();
                    operationName = method.getAnnotation(LoginLogAnnotation.class).operationName();
                }
            }
        }
        /**
         * 第二种方式:
         *      通过用户的ip地址来获取用户的地理位置
         *      会使用到一个外部API(百度)
         *      ---->自己去定义一个工具类(向百度api去发送请求--->再去接收百度api所响应回来的数据)
         */
        // 百度api只能获取静态公网ip(俗称服务器的ip)--->或者获取运营商的手机ip
        // 只能模拟ip地址
        Map<String, Object> addressMap = AddressUtil.getAddresses(TEST_IP, ENCODING);
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(ipAddr);//ip地址
        loginLog.setProvince((String) addressMap.get(PROVINCE));//省份信息
        loginLog.setLoginType(3);//登录类型，3是小程序
        loginLog.setCity((String) addressMap.get(CITY));//城市信息
        String dateString = DateUtil.formatDate(new Date(),FORMAT_TIME);
        loginLog.setCreateTime(dateString);//日志创建时间;
        loginLog.setOpenId(member.getOpenId());//微信传来的OpenID
        loginLog.setOperationType(operationType);//操作类型
        loginLog.setOperationName(operationName);//具体操作事项
        Boolean ifsuccess = repastService.saveLog(loginLog);
        if (ifsuccess){
            return result;//从切面重新返回到controller中
        }
        return null;//表示代码直接结束，不再返回controller
    }
}
