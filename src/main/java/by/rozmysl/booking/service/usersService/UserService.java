package by.rozmysl.booking.service.usersService;

import by.rozmysl.booking.entity.user.MyUser;
import by.rozmysl.booking.entity.user.Role;
import by.rozmysl.booking.exception.MyEntityNotFoundException;
import by.rozmysl.booking.repository.userRepo.UserRepo;
import by.rozmysl.booking.service.mail.MailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.LocaleResolver;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * This class contains all the business logic for working with the User database
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private HttpServletRequest request;

    /**
     * The method loads the user's data by name from the User's database
     * @param username  name of user
     * @return  user details
     * @throws MyEntityNotFoundException  if user doesn't exist
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws MyEntityNotFoundException {
        MyUser myUser = findByUsername(username);
        if (myUser == null) throw new MyEntityNotFoundException("User " + username + " not found");
        return User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .build();
    }

    /**
     * The method searches for a user in the User database by id
     * @param userId  user id
     * @return  user
     */
    public MyUser findUserById(long userId) {
        Optional<MyUser> userFromDb = userRepo.findById(userId);
        return userFromDb.orElse(new MyUser());
    }

    /**
     * The method searches for a user in the User database by name
     * @param username  name of user
     * @return  user
     */
    public MyUser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    /**
     * The method searches for all users in the database of User
     * @return  list of users
     */
    public List<MyUser> allUsers() {
        return userRepo.findAll();
    }

    /**
     * The method writes the data of the new user to the User database and calls the "sendMail" method to send the activation code
     * @param myUser  user
     * @return  the result of checking the truth of saving the new user
     */
    public boolean saveUser(MyUser myUser) {
        if (userRepo.findByUsername(myUser.getUsername()) != null) return false;
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        myUser.setRoles(Collections.singleton(new Role(1, "Юзер")));
        myUser.setActivationCode(UUID.randomUUID().toString());
        myUser.setActive(false);
        userRepo.save(myUser);

        String message;
        if (localeResolver.resolveLocale(request).toString().equals("us")) {
            message = String.format("Hello, %s! \n" + "We are glad to see you on the website of our hotel." +
                            "To activate your account, please follow the link: http://localhost:8080/activate/%s",
                    myUser.getUsername(), myUser.getActivationCode());
        } else message = String.format("Привет, %s! \n" + "Рады Вас видеть на сайте нашей гостиницы." +
                        "Для активации учетной записи перейдите, пожалуйста, по ссылке: http://localhost:8080/activate/%s",
                myUser.getUsername(), myUser.getActivationCode());
        mailSender.sendMail(myUser.getEmail(), "Activation code", message);
        return true;
    }

    /**
     * The method checks the user's activation code
     * @param code  activation code
     * @return  the result of verifying the truth of the activation of the new user
     */
    public boolean activateUser(String code) {
        MyUser myUser = userRepo.findByActivationCode(code);
        if (myUser == null) return false;
        myUser.setActivationCode(null);
        myUser.setActive(true);
        userRepo.save(myUser);
        return true;
    }

    /**
     * The method deletes the user data to the User database
     * @param userId  user id
     */
    public void deleteUser(long userId) {
        if (userRepo.findById(userId).isPresent()) userRepo.deleteById(userId);
    }
}
