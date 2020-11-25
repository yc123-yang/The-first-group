package com.sicnu.config;


//import com.sicnu.component.LoginHandlerInterceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 跨域的过滤机制
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        return new FilterRegistrationBean(new Filter() {
            public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                String method = request.getMethod();
                // this origin value could just as easily have come from a database
                response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type,Last-Modified,device,token");
                if ("OPTIONS".equals(method)) {//检测是options方法则直接返回200
                    response.setStatus(HttpStatus.OK.value());
                } else {
                    chain.doFilter(req, res);
                }
            }

            public void init(FilterConfig filterConfig) {
            }

            public void destroy() {
            }
        });
    }

}

