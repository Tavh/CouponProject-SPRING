package com.tav.coupons.filters;

import java.io.IOException;  


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;

// ********** Not sure it's written correctly yet **********
public class LoginFilter implements Filter {


    private static final String RESOURCE_PATH = "/no-login-required";

	public void destroy() {
		// TODO Auto-generated method stub
	}

// ------------------------------ The method that actually filters-------------------------------------

	// This method makes sure that only a logged-in user can access certain URLs
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(req.getMethod().equalsIgnoreCase("Options")) {
			chain.doFilter(request, response);
		}
		
		String path = ((HttpServletRequest) request).getPathInfo();
		
        if (path.contains(RESOURCE_PATH)) {
            chain.doFilter(request, response);
            return;
        }
        
        if (path.endsWith("login")) {
            chain.doFilter(request, response);
            return;
        }
		
		if (req.getSession(false) != null) {
			chain.doFilter(request, response);
			return;
		}
		
		res.setStatus(401);
		res.setHeader("ErrorCause", "Couldn't find a login session");
	}
	
// ---------------------------------------------------------------------------------------------------

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
