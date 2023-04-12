package com.medicare.OauthConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http .authorizeRequests()
//		GET METHOD 
		.antMatchers("/Medicare/getMedicinesByDisease/{disease}").access("#oauth2.hasScope('read')")
		
		

		
		.anyRequest().authenticated();
	}
}
