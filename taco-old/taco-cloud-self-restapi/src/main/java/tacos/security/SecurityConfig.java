package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	/* In-memory user store
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.passwordEncoder(new BCryptPasswordEncoder())
		.withUser("bill")
		.password(new BCryptPasswordEncoder().encode("123456"))
		.authorities("ROLE_USER")
		.and()
		.withUser("sofica")
		.password(new BCryptPasswordEncoder().encode("123456"))
		.authorities("ROLE_USER");
	} */
	
	/*JDBC-based user store
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(
		"select username, password, enabled from Users " +
		"where username=?")
		.authoritiesByUsernameQuery(
		"select username, authority from UserAuthorities " +
		"where username=?")
		.passwordEncoder(new BCryptPasswordEncoder());
	} */
	
	/* LDAP-backed user store
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth
		.ldapAuthentication()
		.userSearchBase("ou=people")
		.userSearchFilter("(uid={0})")
		.groupSearchBase("ou=groups")
		.groupSearchFilter("member={0}")
		.contextSource()
		.url("ldap://127.0.0.1:389/dc=tacocloud,dc=com")
		.ldif("classpath:users.ldif")
		.and()
		.passwordCompare()
		.passwordEncoder(new BCryptPasswordEncoder())
		.passwordAttribute("userPassword");
	} */
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	      .authorizeRequests()
	    //  .antMatchers("/design", "/orders")
        //  .access("hasRole('ROLE_USER')")
        .antMatchers("/", "/**").access("permitAll")
	        
	      //set the login page when it needs authorization and the page when it's authorized  
	      .and()
	        .formLogin()
	          .loginPage("/login")
	          .defaultSuccessUrl("/design", true)
	      
	      //set the page when it's logout    
	      .and()
	        .logout()
	          .logoutSuccessUrl("/")
	          
	      // Make H2-Console non-secured; for debug purposes
	      .and()
	        .csrf()
	          .ignoringAntMatchers("/h2-console/**")

	      // Allow pages to be loaded in frames from the same origin; needed for H2-Console
	      .and()  
	        .headers()
	          .frameOptions()
	            .sameOrigin()
	      ;
	    
	    //disable csrf
	    http.csrf().disable();
	}
}
