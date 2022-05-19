package com.pb.personalblog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截所有异常进行统一处理

@ControllerAdvice//拦截所有@Controller注解的控制器
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());//获取日志

    @ExceptionHandler(Exception.class)         //标识下列方法可以作为异常处理（括号里为异常信息级别）
    public ModelAndView exceptionHander(HttpServletRequest request, Exception e) throws Exception {//ModelAndView可以返回一个页面加后台输出到前端的信息
        //request返回哪个页面出现异常，e传递异常
        logger.error("Request URL : {},Exception : {}", request.getRequestURL(), e);//日志记录异常信息


//        if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class)!=null){
//            throw  e;
//        }//判断ResponseStatus注解是否存在，存在就抛出异常给他处理，不存在就自己处理（NotFoundException）

        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());//设置返回的页面及异常信息
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;

    }
}
