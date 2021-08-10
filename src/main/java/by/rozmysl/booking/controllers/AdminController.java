package by.rozmysl.booking.controllers;

import by.rozmysl.booking.entity.hotel.Room;
import by.rozmysl.booking.service.hotelService.AdditionalService;
import by.rozmysl.booking.service.hotelService.FoodService;
import by.rozmysl.booking.service.usersService.ReservationService;
import by.rozmysl.booking.service.hotelService.RoomService;
import by.rozmysl.booking.service.usersService.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.stream.Collectors;
/**
 * The class processes administrator requests, creates the appropriate model, and passes it for display
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private AdditionalService additionalService;

    /**
     * The method makes a get request and displays a list of all users on the page
     * @param model  the following attributes have been added: list of all users
     * @return  the allUsers page
     */
    @GetMapping("/admin/allUsers")
    public String getUserList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin/allUsers";
    }

    /**
     * The method removes the user from the database
     * @param userId  user id
     * @param delete  button name
     * @return  redirect the allUsers page
     */
    @PostMapping("/admin/allUsers")
    public String deleteUser(@RequestParam(defaultValue = "") Long userId,
                             @RequestParam(defaultValue = "") String delete) {
        if (delete.equals("delete")) userService.deleteUser(userId);
        return "redirect:/admin/allUsers";
    }

    /**
     * The method makes a get request and displays a list of all reservation on the page
     * @param model  the following attributes have been added: list of all reservations, roomService
     * @return  the allReservation page
     */
    @GetMapping("/admin/allReservation")
    public String getReservationList(Model model) {
        model.addAttribute("allReservation", reservationService.allReservation()).addAttribute("roomService", roomService);
        return "admin/allReservation";
    }

    /**
     * The method makes the following changes to the reservation database: room change, status change, deletion
     * @param room  room id
     * @param changeRoom  button name
     * @param status  reservation status
     * @param reservationId  reservation id
     * @param delete  button name
     * @param changeStatus  button name
     * @return  redirect the allReservation page
     * @throws MessagingException if the message cannot be created
     * @throws IOException if the invoice cannot be created
     */
    @PostMapping("/admin/allReservation")
    public String changeReservation(@RequestParam(name = "room", required = false) Integer room,
                                    @RequestParam(defaultValue = "") String changeRoom,
                                    @RequestParam(name = "status", required = false) String status,
                                    @RequestParam(defaultValue = "") Integer reservationId,
                                    @RequestParam(defaultValue = "") String delete,
                                    @RequestParam(defaultValue = "") String changeStatus) throws MessagingException, IOException {
        if (changeRoom.equals("changeRoom")) reservationService.changeRoom(reservationId, room);
        if (delete.equals("delete")) reservationService.deleteReservation(reservationId);
        if (changeStatus.equals("changeStatus")) reservationService.changeStatusReservation(reservationId, status);
        return "redirect:/admin/allReservation";
    }

    /**
     * The method makes a get request and displays a list of all rooms on the page
     * @param model  the following attributes have been added: list of all rooms
     * @return  the allRooms page
     */
    @GetMapping("/admin/allRooms")
    public String findAllRooms(Model model) {
        model.addAttribute("allRooms", roomService.allRooms());
        return "admin/allRooms";
    }

    /**
     * The method makes the following changes to the rooms database: price change, deletion
     * @param price  room price
     * @param roomId   room id
     * @param changePrice  button name
     * @param delete  button name
     * @return  redirect the allRooms page
     */
    @PostMapping("/admin/allRooms")
    public String changeRoomPrice(@RequestParam(name = "price", required = false) Double price,
                              @RequestParam(defaultValue = "") Integer roomId,
                              @RequestParam(defaultValue = "") String changePrice,
                              @RequestParam(defaultValue = "") String delete) {
        if (changePrice.equals("changePrice")) roomService.changeRoomPrice(roomId, price);
        if (delete.equals("delete")) roomService.deleteRoom(roomId);
        return "redirect:/admin/allRooms";
    }

    /**
     * The method makes a get request and displays a list of all rooms on the page
     * @param model  the following attributes have been added: list of all rooms, list of all type rooms
     * @return  the changeRoomParameters page
     */
    @GetMapping("/admin/changeRoomParameters")
    public String getRoomParameters(Model model) {
        model.addAttribute("rooms", roomService.allRooms()).addAttribute("parameters", roomService.findAllTypeRooms());
        return "admin/changeRoomParameters";
    }

    /**
     * The method makes the following changes to the rooms database: room parameters change, price change
     * @param roomId  room id
     * @param parameters  room parameters
     * @param changeRoomParameters  button name
     * @param price  room price
     * @param changeRoomPrice  button name
     * @return  redirect the changeRoomParameters page
     */
    @PostMapping("/admin/changeRoomParameters")
    public String changeRoomParameters(@RequestParam(defaultValue = "") int roomId,
                                       @RequestParam(name = "room", required = false) Integer parameters,
                                       @RequestParam(defaultValue = "") String changeRoomParameters,
                                       @RequestParam(name = "price", required = false) Double price,
                                       @RequestParam(defaultValue = "") String changeRoomPrice) {
        if (changeRoomParameters.equals("changeRoomParameters")) roomService.changeRoomParameters(roomId, parameters);
        if (changeRoomPrice.equals("changeRoomPrice")) roomService.changeRoomPrice(roomId, price);
        return "redirect:/admin/changeRoomParameters";
    }

    /**
     * The method makes a get request and displays a list of all services on the page
     * @param model  the following attributes have been added: list of type food, list of type services
     * @return  the changeServicesPrice page
     */
    @GetMapping("/admin/changeServicesPrice")
    public String getAllServices(Model model) {
        model.addAttribute("food", foodService.allFood()).addAttribute("services", additionalService.allAdditionalService());
        return "admin/changeServicesPrice";
    }

    /**
     * The method makes the following changes to the food and additional services database: food price change, services price change
     * @param foodId  food id
     * @param foodPrice  food price
     * @param changeFoodPrice  button name
     * @param serviceId  service id
     * @param servicePrice  service price
     * @param changeServicePrice  button name
     * @return  redirect the changeServicesPrice page
     */
    @PostMapping("/admin/changeServicesPrice")
    public String changeServices(@RequestParam(defaultValue = "") String foodId,
                                 @RequestParam(name = "foodPrice", required = false) Double foodPrice,
                                 @RequestParam(defaultValue = "") String changeFoodPrice,
                                 @RequestParam(defaultValue = "") String serviceId,
                                 @RequestParam(name = "servicePrice", required = false) Double servicePrice,
                                 @RequestParam(defaultValue = "") String changeServicePrice) {
        if (changeFoodPrice.equals("changeFoodPrice")) foodService.changeFoodPrice(foodId, foodPrice);
        if (changeServicePrice.equals("changeServicePrice"))
            additionalService.changeServicePrice(serviceId, servicePrice);
        return "redirect:/admin/changeServicesPrice";
    }

    /**
     * The method makes a get request and displays a form on the page for creating a new room
     * @param model  the following attributes have been added: a form for creating a new room, list of all type rooms, roomService
     * @return  the addRoom page
     */
    @GetMapping("/admin/addRoom")
    public String inputRoom(Model model) {
        model.addAttribute("formRoom", new Room()).addAttribute("roomService", roomService)
                .addAttribute("room", roomService.findAllTypeRooms().stream().map(Room::getType).collect(Collectors.toSet()));
        return "admin/addRoom";
    }

    /**
     * The method makes the following changes to the room database: creates a new room, and also checks the correctness of the entered data
     * @param formRoom  room form
     * @param bindingResult  binding result in the room form
     * @param model  the following attributes have been added: list of all type rooms
     * @return  redirect the admin page
     */
    @PostMapping("/admin/addRoom")
    public String addRoom(@ModelAttribute("formRoom") @Valid Room formRoom, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "redirect:/admin/addRoom";
        if (formRoom.getPrice() != 0) {
            roomService.saveRoom(formRoom);
            return "redirect:/admin/admin";
        }
        if (roomService.checkRoomByTypeAndSleeps(formRoom.getType(), formRoom.getSleeps())) {
            model.addAttribute("room", roomService.findAllTypeRooms().stream().map(Room::getType).collect(Collectors.toSet()));
            return "admin/addRoom";
        } else {
            formRoom.setPrice(roomService.findRoomByTypeAndSleeps(formRoom.getType(), formRoom.getSleeps()).getPrice());
        }
        roomService.saveRoom(formRoom);
        return "redirect:/admin/admin";
    }
}
