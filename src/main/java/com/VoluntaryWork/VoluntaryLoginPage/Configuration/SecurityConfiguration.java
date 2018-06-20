package com.VoluntaryWork.VoluntaryLoginPage.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.VoluntaryWork.VoluntaryLoginPage.Controller.LoginController;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.IGNORED_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DataSource dataSource;
    @Value("${spring.queries.users-queries}")
    private String userQuery;
    @Value("${spring.queries.roles-queries}")
    private String roleQuery;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth.
		jdbcAuthentication()
			.usersByUsernameQuery(userQuery)
			.authoritiesByUsernameQuery(roleQuery)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
    	
    	
    }
    @Override
	protected void configure(HttpSecurity http)throws Exception{
		
		http.
		authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/registration").permitAll()
			.antMatchers("/host/**").access("HOST").anyRequest()
			.authenticated().and().csrf().disable().formLogin()
			.loginPage("/login").failureUrl("/login?error=true")
			.defaultSuccessUrl("/host/home")
			.usernameParameter("email")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").and().exceptionHandling()
			.accessDeniedPage("/access-denied");
		
	
	
		}
		
    	
    
    
    @Override
    public void configure(WebSecurity web)throws Exception{
	   web.ignoring()
	   .antMatchers("/resources/**","/static/**","/css/**","/js/**","/images/**");
   }
}
