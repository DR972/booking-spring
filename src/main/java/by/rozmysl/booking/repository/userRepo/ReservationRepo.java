package by.rozmysl.booking.repository.userRepo;

import by.rozmysl.booking.entity.user.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
/**
 * The interface is used to store data about Reservation
 */
public interface ReservationRepo extends JpaRepository<Reservation, Integer> {
    /**
     * The method searches for all reservations the Reservation database between departure and arrival
     * @param departure  departure of reservation
     * @param arrival  arrival of reservation
     * @return  list of Reservation
     */
    List<Reservation> findAllByArrivalLessThanEqualAndDepartureGreaterThanEqual(LocalDate departure, LocalDate arrival);

    /**
     * The method searches for a reservation the Reservation database by user id
     * @param user  user id
     * @return  list of Reservation
     */
    List<Reservation> findAllByUser(long user);
}
