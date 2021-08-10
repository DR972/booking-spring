package by.rozmysl.booking.repository.hotelRepo;

import by.rozmysl.booking.entity.hotel.Food;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * The interface is used to store data about Food
 */
public interface FoodRepo extends JpaRepository<Food, String> {
    /**
     * The method searches for a food the Food database by type
     * @param type  type of food
     * @return  object Food
     */
    Food findByType(String type);
}
