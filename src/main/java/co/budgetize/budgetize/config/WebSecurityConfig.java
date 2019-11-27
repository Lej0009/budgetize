package co.budgetize.budgetize.config;

/* We need to go to Spring Security library to be able
to encode user's password before saving it to the database.
And you need to declare a Spring BEAN for encoding the password.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    // Since we aren't using Security to secure the page, so
    // Override this method with empty code
    // to disable the default Spring Boot security.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // empty code
    }
}
