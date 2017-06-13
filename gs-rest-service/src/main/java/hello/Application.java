package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)").userDetailsService(userDetailsService());
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                if (username.equals("cid")) {
//                    return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//                }
//                throw new UsernameNotFoundException("User not found!");
//            }
//        };
//    }
}