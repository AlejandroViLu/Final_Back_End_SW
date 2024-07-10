package com.finalSW.Security.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.finalSW.Security.jwt.JwtEntryPoint;
import com.finalSW.Security.jwt.JwtFilter;
import com.finalSW.Security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurityConfig {
	@Autowired
	UserDetailsServiceImpl udsi;
	@Autowired
	PasswordEncoder pe;
	@Autowired
	JwtEntryPoint jep;
	@Autowired
	JwtFilter jf;

	AuthenticationManager am;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(udsi).passwordEncoder(pe);
		am = builder.build();
		http.authenticationManager(am);
		http.csrf().disable();
		http.cors();
		http.authorizeHttpRequests().antMatchers("/auth/**").permitAll()
		.anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(jep);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jf, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(udsi).passwordEncoder(pe);
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults()).csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests.antMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()).exceptionHandling(handling -> handling.authenticationEntryPoint(jep)).sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jf, UsernamePasswordAuthenticationFilter.class);
	}
	*/
}
