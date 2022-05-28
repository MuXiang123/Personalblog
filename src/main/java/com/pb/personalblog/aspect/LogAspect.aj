package com.pb.personalblog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhk
 * @date 2022/5/27 17:20
 * 日志切面，打印日志到控制台
 */
//开启切面和注解扫描
@Aspect
@Component
public class LogAspect {
    /**输出日志
     *
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 切面
     * 拦截所有访问
     */
    @Pointcut("execution(* com.pb.personalblog.controller.*.*(..))")
    public void log() {//定义切面
    }

    /**
     * 在切面之前执行，
     * @param joinPoint 连接点
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("----------------doBefore---------------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //类名+方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //参数
        Object[] args = joinPoint.getArgs();
        //封装请求日志并且打印
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request:{}", requestLog);

    }

    /**
     * 切面后执行
     */
    @After("log()")
    public void doAfter() {
        logger.info("----------------doAfter---------------");
    }

    /**
     * 拦截返回后的内容并输出
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterRuturn(Object result) {
        logger.info("Result : {}", result);
    }

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }


        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args='" + args + '\'' +
                    '}';
        }
    }
}
