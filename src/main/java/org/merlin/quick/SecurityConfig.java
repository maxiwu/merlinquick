package org.merlin.quick;

import java.security.SecureRandom;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	JdbcUserDetailsManager detailManager;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {		

//		auth.inMemoryAuthentication().withUser("merlin").password("123456")
//				.roles("ADMIN");

		// auth.inMemoryAuthentication().withUser("ken").password("123456").roles("ADMIN");
		// auth.inMemoryAuthentication().withUser("james").password("123456").roles("SUPERADMIN");

		/*
		 * auth.userDetailsService(userDetailsManager()).passwordEncoder(
		 * bCryptPasswordEncoder);
		 */
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder)
				.usersByUsernameQuery(
						"select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery(
						"select username, role from user_roles where username=?");
		/*
		 * detailManager = (JdbcUserDetailsManager) auth
		 * .getDefaultUserDetailsService();
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests()
				.antMatchers("/service", "/css/**", "/fonts/**", "/modal/**",
						".resources/**", "/public/**").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().permitAll();
		// http.addFilterAfter(RESTfulFilter, SessionManagementFilter.class);
	}

	@Bean
	public JdbcUserDetailsManager userDetailsManager() {
		return detailManager;
	}

	@Bean
	@Qualifier("myCrypt") 
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		String salt = "dXJlamcgakUjUllUQCQlR0VSRVQgVmkyd3ItMDI1eTZoZ2tqbSB3ZTNAdEVHRFNGIEVXUnQgM2txW29pZjQzLWZqNSBtO2ZlZCAjJVlHJCUgZmg=";
		return new BCryptPasswordEncoder(4, new SecureRandom(salt.getBytes()));
	}

	/*
	 * @Bean public JdbcUserDetailsManager userDetailsManager() {
	 * JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
	 * manager.setDataSource(dataSource); manager.setUsersByUsernameQuery(
	 * "select username,password, enabled from users where username=?");
	 * manager.setAuthoritiesByUsernameQuery(
	 * "select username, role from user_roles where username=?");
	 * manager.setRolePrefix("ROLE_"); return manager; }
	 */

	/*
	 * @Bean SessionRegistry sessionRegistry() { return new
	 * SessionRegistryImpl(); }
	 */
}
