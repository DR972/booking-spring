package by.rozmysl.booking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
/**
 * The class verifies the success of authentication
 */
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * The method sets the user's role and redirects to the desired page
     * @param httpServletRequest  request
     * @param httpServletResponse  response
     * @param auth  authenticator
     * @throws IOException  occurs when it is not possible to redirect to the desired page
     */

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication auth) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
        if (roles.stream().anyMatch(r -> r.contains("Админ"))) httpServletResponse.sendRedirect("/admin/admin");
        else httpServletResponse.sendRedirect("/user/user");
    }
}
