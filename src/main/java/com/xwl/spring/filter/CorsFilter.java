package com.xwl.spring.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CorsFilter",urlPatterns = "/*")
public class CorsFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// 指定允许其他域名访问
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		// 响应类型
		httpResponse.setHeader("Access-Control-Allow-Methods", " GET, POST, OPTIONS,PUT,DELETE");
		// 响应头设置
		httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token,token");
		// 响应有效时间
		httpResponse.setHeader("Access-Control-Max-Age", "86400");
		if("OPTIONS".equals(httpRequest.getMethod())) {

			// httpResponse.setStatus(200);
		}
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
