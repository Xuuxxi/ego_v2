package com.handsUp.ego_v2.filter;

import com.alibaba.fastjson.JSON;
import com.handsUp.ego_v2.common.BaseContext;
import com.handsUp.ego_v2.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/3
 */

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*",asyncSupported = true)
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();

        log.info("拦截到请求 {}",requestURI);

        String[] urls = {
                "/user/login",
                "/user/logout",
                "/user/register"
        };

        boolean check = Check(urls, requestURI);

        if(check){
            log.info("无需过滤处理");
            filterChain.doFilter(request,response);
            return;
        }

        if(request.getSession().getAttribute("user") != null){
            log.info("该用户已登录，id = {}",request.getSession().getAttribute("user"));

            Long user = (Long) request.getSession().getAttribute("user");
            log.info("Filter set id = {}",user);
            BaseContext.setCurrentId(user);

            filterChain.doFilter(request,response);
            return;
        }

        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    public boolean Check(String[] urls,String requestURI){
        for(String url : urls){
            boolean flag = PATH_MATCHER.match(url, requestURI);
            if(flag) return true;
        }

        return false;
    }
}
