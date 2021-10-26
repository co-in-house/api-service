package com.inhouse.filter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inhouse.util.ConsoleLogger;

public class AccesslogFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {}

    @Override
    public void destroy() {}
    
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain) throws IOException, ServletException {
        
        LocalDateTime requestDateTime = LocalDateTime.now();

        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;
        String uri = request.getRequestURI();
        String method = request.getMethod();
        
        
        ConsoleLogger.info(new StringBuffer("[Request]").append(" ").append(method).append(" ").append(uri).toString());
        
        try{
            filterChain.doFilter(sRequest, sResponse);	
        } finally {
            LocalDateTime responseDateTime = LocalDateTime.now();
            ConsoleLogger.info(new StringBuffer("[Response]").append(" ").append(method).append(" ")
                            .append(uri).append(" ").append(response.getStatus()).append(" ")
                            .append(Duration.between(requestDateTime, responseDateTime).toString())
                        .toString());
        }
    }

  
}
