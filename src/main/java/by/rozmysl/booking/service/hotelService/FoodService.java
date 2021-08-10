package by.rozmysl.booking.service.hotelService;

import by.rozmysl.booking.entity.hotel.Food;
import by.rozmysl.booking.repository.hotelRepo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * This class contains all the business logic for working with the Food database
 */
@Service
public class FoodService {
    @Autowired
    private FoodRepo foodRepo;

    /**
     * The method searches for all types of food in the database of Food
     * @return  list of Food
     */
    public List<Food> allFood() {
        return foodRepo.findAll();
    }

    /**
     * The method changes the price of a service in the database of Food to a new one
     * @param foodId  food id
     * @param price  food price
     */
    public void changeFoodPrice(String foodId, double price) {
        Food food = foodRepo.findByType(foodId);
        food.setPrice(price);
        foodRepo.save(food);
    }
}
