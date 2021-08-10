package by.rozmysl.booking.service.hotelService;

import by.rozmysl.booking.entity.hotel.AdditionalServices;
import by.rozmysl.booking.repository.hotelRepo.AdditionalServicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * This class contains all the business logic for working with the Additional Services database
 */
@Service
public class AdditionalService {
    @Autowired
    private AdditionalServicesRepo additionalServicesRepo;

    /**
     * The method searches for all services in the database of Additional Services
     * @return  list of Additional Services
     */
    public List<AdditionalServices> allAdditionalService() {
        return additionalServicesRepo.findAll();
    }

    /**
     * The method changes the price of a service in the database of additional services to a new one
     * @param serviceId  service id
     * @param price  service price
     */
    public void changeServicePrice(String serviceId, double price) {
        AdditionalServices service = additionalServicesRepo.findByType(serviceId);
        service.setPrice(price);
        additionalServicesRepo.save(service);
    }
}
