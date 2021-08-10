package by.rozmysl.booking.controllers;

import by.rozmysl.booking.entity.user.Reservation;
import by.rozmysl.booking.service.hotelService.AdditionalService;
import by.rozmysl.booking.service.hotelService.FoodService;
import by.rozmysl.booking.service.usersService.ReservationService;
import by.rozmysl.booking.service.hotelService.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.stream.Collectors;
/**
 * The class processes reservation requests, creates the appropriate model and passes it for display
 */
@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private AdditionalService additionalService;
    private Reservation oldForm;
    private String missed = "";

    /**
     * The method makes a get request and displays a form on the page for creating a new reservation
     * @param model  the following attributes have been added: a form for creating a new reservation, list of type food, list of type services
     * @return  the date page
     */
    @GetMapping("/user/date")
    public String getDate(Model model) {
        model.addAttribute("form", new Reservation()).addAttribute("food", foodService.allFood())
                .addAttribute("services", additionalService.allAdditionalService());
        return "user/date";
    }

    /**
     * This method saves the form of the new reservation, and also checks the correctness of the entered data
     * @param form  reservation form
     * @param bindingResult  binding result in the reservation form
     * @param model  the following attributes have been added: a date error,  list of type food, list of type services
     * @return  redirect the reservation page
     */
    @PostMapping("/user/date")
    public String addDate(@ModelAttribute("form") @Valid Reservation form, BindingResult bindingResult, Model model) {
        if (form == null) return "redirect:/user/date";
        model.addAttribute("food", foodService.allFood()).addAttribute("services", additionalService.allAdditionalService());
        if (bindingResult.hasErrors()) return "user/date";
        if (form.getArrival().isBefore(LocalDate.now())) {
            model.addAttribute("dateError", "dateError");
            return "user/date";
        }
        oldForm = form;
        oldForm.setDeparture(oldForm.getArrival().plusDays(oldForm.getDays()));
        return "redirect:/user/reservation";
    }

    /**
     * The method makes a get request and displays the types of available rooms according to the booking form on the page
     * @param model  the following attributes have been added: a form for creating a new reservation, list of free room types,
     *               message about the lack of available rooms
     * @return  the reservation page
     */
    @GetMapping("/user/reservation")
    public String getReservation(Model model) {
        if (oldForm == null) return "redirect:/user/date";
        model.addAttribute("form", oldForm).addAttribute("missed", missed);
        missed = "";
        if (roomService.findFreeRoomsByTypes(oldForm).stream().noneMatch(r -> r.getSleeps() >= oldForm.getPersons())) {
            model.addAttribute("noAvailable", "noAvailable");
            return "user/reservation";
        }
        model.addAttribute("allRooms", roomService.findFreeRoomsByTypes(oldForm).stream()
                .filter(r -> r.getSleeps() >= oldForm.getPersons()).collect(Collectors.toList()));
        return "user/reservation";
    }

    /**
     * The method makes the following changes to the reservation database: creates a new reservation,
     * and also performs an additional check of the availability of the selected room
     * @param roomId  room id
     * @param model  the following attributes have been added: list of free room types, message about the lack of available rooms
     * @return  redirect the confirmation page
     */
    @PostMapping("/user/reservation")
    public String addReservation(@RequestParam(defaultValue = "") int roomId, Model model) {
        if (!roomService.findFreeRooms(oldForm).contains(roomService.findByNumber(roomId))) {
            if (roomService.checkFreeRoomByTypeAndSleeps(oldForm)) {
                missed = "missed";
                return "redirect:/user/reservation";
            } else oldForm.setNumber(roomService.findFreeRoomByTypeAndSleeps(oldForm).getNumber());
        } else oldForm.setNumber(roomService.findByNumber(roomId).getNumber());
        model.addAttribute("form", reservationService.saveReservation(oldForm));
        return "redirect:/user/confirmation";
    }

    /**
     * The method makes a get request and displays the booking information on the page
     * @param model  the following attributes have been added: a form for creating a new reservation
     * @return  the confirmation page
     */
    @GetMapping("/user/confirmation")
    public String getFinalReservation(Model model) {
        model.addAttribute("form", oldForm);
        return "user/confirmation";
    }
}