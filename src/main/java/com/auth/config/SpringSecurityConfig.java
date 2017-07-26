package com.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired 
	private AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.clients-query}")
	private String clientsQuery;
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

//	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and()
		.httpBasic().authenticationEntryPoint(authEntryPoint);
		
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("api/v1/ebank/auth/login")
		.and().ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.jdbcAuthentication().usersByUsernameQuery(clientsQuery)
//								 .authoritiesByUsernameQuery(rolesQuery)
////								 .passwordEncoder(passwordEncoder())
//								 .dataSource(dataSource);
		auth.inMemoryAuthentication().withUser("testuser")
		.password("testpass").roles("admin");
		
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	
	
	

	
	

}
