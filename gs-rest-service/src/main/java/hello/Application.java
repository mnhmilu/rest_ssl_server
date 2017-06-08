package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
    @Value("${allowed.user}")
    private String ALLOWED_USER;

    @Bean
    public UserDetailsService userDetailsService () {

        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
                if (username.equals(ALLOWED_USER)) {
                    final User user = new User(username, "", AuthorityUtils.createAuthorityList("ROLE_SSL_USER"));
                    return user;
                }
                return null;
            }
        };
    }	
    
    
    
    
    
    
    
}
