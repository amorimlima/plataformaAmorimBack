package br.com.muranodesign.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter implements Filter{
	
	private FilterConfig filterConfig;
	
	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {	
		filterChain.doFilter(request, response);
	}

	
	public FilterConfig getFilterConfig() {
        return filterConfig;
    }

	@Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
	
	
	
}