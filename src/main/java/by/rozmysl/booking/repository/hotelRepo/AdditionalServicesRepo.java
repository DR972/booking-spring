package by.rozmysl.booking.repository.hotelRepo;

import by.rozmysl.booking.entity.hotel.AdditionalServices;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * The interface is used to store data about Additional Services
 */
public interface AdditionalServicesRepo extends JpaRepository<AdditionalServices, String> {
    /**
     * The method searches for a services the Additional Services database by Type
     * @param type  type of service
     * @return  object Additional Services
     */
    AdditionalServices findByType(String type);
}
