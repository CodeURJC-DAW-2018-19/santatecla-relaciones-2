package urjcdaw11.relman.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;



@Order(1)
@Configuration
public class SecurityConfigRest extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/api/**");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logIn").authenticated();
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/units").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/unit/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**").permitAll();	
		
		
		
		http.authorizeRequests().anyRequest().permitAll();
		
		http.csrf().disable();
		http.httpBasic();
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
}
