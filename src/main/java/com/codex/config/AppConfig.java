package com.codex.config;
import org.springframework.context.annotation.Bean;
/* ### This is a Configuration file 
 * 
 * */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class AppConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		/**
		 * ### JwtTokenValidator
		 * # when user will request for our api this will check wether the end point is enlisted or not 
		 * # if the end point is /api/ then is authenticated and if the end point is other then it should permit all
		 * # but when it is valid end point it will go the JwtTokenValidator to verify jwttoken which is send by user 
		 */
		//whaterver enpointwhich will inside api will be secure
		http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(Authorizae->Authorizae.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
		.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
		.csrf(csrf->csrf.disable())
		.cors(cors->cors.configurationSource(corsConfigrationSource()));
		
		return http.build();
	}
	
	/*
	 * #  When we connect front end with back end browser will throw the cors configuration error
	 * # to avoid this corsconfiguration is use
	 * # here we define this this should accept the backend
	 * # using spring boot when other website or port will try to access our backend then it will throw cors configuration error
	 * */
	private CorsConfigurationSource corsConfigrationSource() {
		return null;
		
	}
}
