package by.rozmysl.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
/**
 * The class defines the Spring MVC configuration
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * The method adds view controllers
     * @param registry view controller recorder
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/user").setViewName("user/user");
        registry.addViewController("/user/date").setViewName("user/date");
        registry.addViewController("/user/reservation").setViewName("user/reservation");
        registry.addViewController("/user/userReservation").setViewName("user/userReservation");
        registry.addViewController("/user/confirmation").setViewName("user/confirmation");
        registry.addViewController("/user/price").setViewName("user/price");
        registry.addViewController("/admin/admin").setViewName("admin/admin");
        registry.addViewController("/admin/allUsers").setViewName("admin/allUsers");
        registry.addViewController("/admin/allReservation").setViewName("admin/allReservation");
        registry.addViewController("/admin/changeRoomParameters").setViewName("admin/changeRoomParameters");
        registry.addViewController("/admin/addRoom").setViewName("admin/addRoom");
        registry.addViewController("/admin/changeServicesPrice").setViewName("admin/changeServicesPrice");
        registry.addViewController("/admin/allRooms").setViewName("admin/allRooms");
    }

    /**
     * The method adds interceptors
     * @param registry  interceptors recorder
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * The method intercepts locale changes
     * @return  the intercepted locale change
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * The Locale recognizer method
     * @return  session locale
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }
}
