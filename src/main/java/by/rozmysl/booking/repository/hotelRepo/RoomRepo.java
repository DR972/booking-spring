package by.rozmysl.booking.repository.hotelRepo;

import by.rozmysl.booking.entity.hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * The interface is used to store data about Room
 */
public interface RoomRepo extends JpaRepository<Room, Integer> {
    /**
     * The method searches for a room in the database of Room by number
     * @param number  number of room
     * @return  object Room
     */
    Room findByNumber(int number);
}
