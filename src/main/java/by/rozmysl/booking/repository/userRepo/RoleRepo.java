package by.rozmysl.booking.repository.userRepo;

import by.rozmysl.booking.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * The interface is used to store data about Role
 */
public interface RoleRepo extends JpaRepository<Role, Long> {
}
