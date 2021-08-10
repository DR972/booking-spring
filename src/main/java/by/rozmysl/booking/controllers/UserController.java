package by.rozmysl.booking.controllers;

import by.rozmysl.booking.service.hotelService.AdditionalService;
import by.rozmysl.booking.service.hotelService.FoodService;
import by.rozmysl.booking.service.hotelService.RoomService;
import by.rozmysl.booking.service.usersService.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.mail.MessagingException;
import java.io.IOException;
/**
 * The class processes user requests creates the appropriate model and passes it for display
 */
@Controller
public class UserController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private AdditionalService additionalService;
    @Autowired
    private ReservationService reservationService;

    /**
     * The method makes a get request and displays the price list for hotel services on the page
     * @param model  the following attributes have been added: list of all type rooms, list of type food, list of type services
     * @return  the price page
     */
    @GetMapping("/user/price")
    public String getPrice(Model model) {
        model.addAttribute("allTypeRooms", roomService.findAllTypeRooms()).addAttribute("allTypeFoods", foodService.allFood())
                .addAttribute("allServices", additionalService.allAdditionalService());
        return "user/price";
    }

    /**
     * The method makes a get request and displays information about the booking of this user on the page
     * @param model  the following attributes have been added: list of all user reservations
     * @return  the userReservation page
     */
    @GetMapping("/user/userReservation")
    public String getUserReservationList(Model model) {
        model.addAttribute("userReservation", reservationService.findReservationByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "user/userReservation";
    }

    /**
     * The method makes the following changes to the reservation database: deletion
     * @param reservationId  reservation id
     * @param delete  button name
     * @return  redirect the userReservation page
     * @throws MessagingException  if the message cannot be created
     * @throws IOException if the invoice cannot be deleted
     */
    @PostMapping("/user/userReservation")
    public String deleteReservation(@RequestParam(defaultValue = "") int reservationId,
                                    @RequestParam(defaultValue = "") String delete) throws MessagingException, IOException {
        if (delete.equals("delete")) reservationService.deleteReservation(reservationId);
        return "redirect:/user/userReservation";
    }
}
