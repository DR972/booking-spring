/**
 * The class defines the Spring Security configuration
 */
package by.rozmysl.booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    /**
     * The method encrypts passwords using the BCrypt method
     * @return the encrypted password
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * The method defines the Security configuration
     * @param httpSecurity httpSecurity
     * @throws Exception may occur after disabling csrf
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers().frameOptions().disable()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedPage("/error")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("Админ")
                .antMatchers("/user/**").hasRole("Юзер")
                .antMatchers("/", "/login", "/registration", "/activate/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/").permitAll()
                        .successHandler(authenticationSuccessHandler)
                        .failureUrl("/login?error=true")
                )
                .logout().permitAll()
                .logoutSuccessUrl("/");
    }

    /**
     * This method defines the user authentication configuration
     * @param auth authenticator
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
}