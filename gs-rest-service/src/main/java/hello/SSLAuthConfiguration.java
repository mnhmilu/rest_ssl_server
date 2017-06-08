package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
// In order to use @PreAuthorise() annotations later on...
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SSLAuthConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${allowed.user}")
	private String ALLOWED_USER;

	@Value("${server.ssl.client.regex}")
	private String CN_REGEX;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers("/url-path-to-protect")
				.authenticated()
				// Specify the URL path(s) requiring authentication...
				.and().x509()
				// ... and that x509 authentication is enabled
				.subjectPrincipalRegex(CN_REGEX)
				.userDetailsService(userDetailsService);
	}

	@Autowired
	// Simplified case, where the application has only one user...
	public void configureGlobal(final AuthenticationManagerBuilder auth)
			throws Exception {
		// ... whose username is defined in the application's properties.
		auth.inMemoryAuthentication().withUser(ALLOWED_USER).password("")
				.roles("SSL_USER");
	}

}