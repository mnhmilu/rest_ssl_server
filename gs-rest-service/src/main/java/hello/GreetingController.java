package hello;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
	
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/greeting")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name,Principal principal) {
    
    	
    	UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
    	
    	log.info("User is :"+ currentUser.getUsername());
    	
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
