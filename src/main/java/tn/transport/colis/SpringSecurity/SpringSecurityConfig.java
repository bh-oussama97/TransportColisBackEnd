package tn.transport.colis.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({"tn.transport.colis.controller", "tn.transport.colis.entity", 
"tn.transport.colis.service"})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 http.csrf().disable();
		// http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().permitAll();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/servlet/user/**", "/servlet/accounting/export/excel", "/personalMsg", "/**").permitAll()
				.anyRequest().authenticated().and().cors();

		http.apply(new JwtTokenConfigurer(tokenProvider));

		/*
		 * http.csrf().disable().sessionManagement().sessionCreationPolicy(
		 * SessionCreationPolicy.STATELESS).and()
		 * .authorizeRequests().antMatchers("/user/authenticate").permitAll().
		 * anyRequest().authenticated(); http.apply(new
		 * JwtTokenConfigurer(tokenProvider));
		 */

	}

}
