package com.filim.program.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class ExeptionHandil implements HandlerInterceptor
{
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
        int a=response.getStatus();
        if(a==500)
        {
        	response.sendRedirect("/loginProses");
        	return false;
        }
		return true;
	}
}
