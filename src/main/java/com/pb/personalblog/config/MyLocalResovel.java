package com.pb.personalblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author zhk
 * @date 2022/6/1 8:50
 * i18n国际化的区域解析器
 */
@Configuration
public class MyLocalResovel implements LocaleResolver {
    /**
     * 自定义区域解析方式
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 获取页面手动切换传递的语言参数 l
        String l = httpServletRequest.getParameter("l");
        // 获取请求头自动传递的语言参数 Accept-Language
        String header = httpServletRequest.getHeader("Accept-Language");
        Locale locale = null;
        // 如果手动切换参数不为空，就根据手动参数进行语言切换，否则默认根据请求头信息切换
        if (!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
            locale = new Locale(split[0], split[1]);
        } else {
            // Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7
            String[] splits = header.split(",");
            String[] split = splits[0].split("-");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, @Nullable
            HttpServletResponse httpServletResponse, @Nullable Locale locale) {
    }

    /**
     * 将自定义的 MyLocalResovel 类重新注册为一个类型 LocaleResolver 的 Bean 组件
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResovel();
    }
}

