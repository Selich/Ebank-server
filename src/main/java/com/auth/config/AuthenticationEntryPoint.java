package com.auth.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint{
	
	@Override
	public void afterPropertiesSet() throws Exception{
		setRealmName("DeveloperStack");
		super.afterPropertiesSet();
	}
	
	public void commence(HttpServletRequest req,
			             HttpServletResponse res,
			             AuthenticationException authEx) throws IOException, ServletException {
		
		res.addHeader("WWW-Autheticate", "Basic realm="+ getRealmName());
		
		res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		PrintWriter writer = res.getWriter();
		
		writer.println("HTTP Status 401 " + authEx.getMessage());

		
	}
	
	
	
	

}
