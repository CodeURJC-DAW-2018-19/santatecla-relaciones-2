package urjcdaw12.relman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import urjcdaw12.relman.users.UserRepositoryAuthenticationProvider;

@Order(2)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		

		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/error").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/register").permitAll();

		http.authorizeRequests().antMatchers("/*").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/rel/**").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/addRelationOrigin/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/addRelationDestiny/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/deleteRelation/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/deleteRelationDestiny/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/addCard/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/saveImage/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/image/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/page/*").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/delete/*").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/addUnit").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/removeTab/*").hasAnyRole("USER");

		http.authorizeRequests().antMatchers("/assets/**").permitAll();
		http.authorizeRequests().anyRequest().permitAll();

		http.formLogin().loginPage("/");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/error");

		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
}
