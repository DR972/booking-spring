package by.rozmysl.booking.repository.userRepo;

import by.rozmysl.booking.entity.user.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * The interface is used to store data about User
 */
public interface UserRepo extends JpaRepository<MyUser, Long> {
    /**
     * The method searches for a user in the database of User by username
     * @param username  username
     * @return  object User
     */
    MyUser findByUsername(String username);

    /**
     * The method searches for a user in the database of User by activation code
     * @param code  activation code
     * @return  object User
     */
    MyUser findByActivationCode(String code);
}
