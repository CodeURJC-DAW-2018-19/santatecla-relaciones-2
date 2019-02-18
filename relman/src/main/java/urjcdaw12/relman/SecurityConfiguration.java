package urjcdaw12.relman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable(); // Para que vaya la consola H2

		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/error").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();

		// http.authorizeRequests().antMatchers("/*").hasAnyRole("USER");

		http.authorizeRequests().anyRequest().permitAll();// TODO Solo para pruebas

		http.formLogin().loginPage("/");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/error");

		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		//http.csrf().disable(); // TODO Usar tokens
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
}
