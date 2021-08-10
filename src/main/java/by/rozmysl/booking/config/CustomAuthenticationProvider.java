package by.rozmysl.booking.config;

import by.rozmysl.booking.entity.user.MyUser;
import by.rozmysl.booking.entity.user.Role;
import by.rozmysl.booking.service.usersService.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
/**
 * The class provides user authentication
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * The method verifies the user's authentication and activation
     * @param auth  authenticator
     * @return  username, password, authentication token
     */
    @Override
    public Authentication authenticate(Authentication auth) {
        MyUser myUser = userService.findByUsername(auth.getName());
        if (myUser == null || !passwordEncoder.matches(auth.getCredentials().toString(), myUser.getPassword())) {
            throw new BadCredentialsException("loginError");
        }
        if (!myUser.getActive()) throw new BadCredentialsException("activeError");
        UserDetails principal = User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getRoles().stream().map(Role::getRole).toArray(String[]::new))
                .build();
        return new UsernamePasswordAuthenticationToken(
                principal, passwordEncoder.encode(auth.getCredentials().toString()), principal.getAuthorities());
    }

    /**
     * The method compares the authentication data with the token
     * @param authentication  authenticator
     * @return true if authentication is successful and false if it is unsuccessful
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
