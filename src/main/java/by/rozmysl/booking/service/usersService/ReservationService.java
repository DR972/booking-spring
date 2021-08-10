package by.rozmysl.booking.service.usersService;

import by.rozmysl.booking.entity.user.Reservation;
import by.rozmysl.booking.entity.hotel.Room;
import by.rozmysl.booking.repository.userRepo.ReservationRepo;
import by.rozmysl.booking.service.hotelService.RoomService;
import by.rozmysl.booking.service.mail.Invoice;
import by.rozmysl.booking.service.mail.MailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
/**
 * This class contains all the business logic for working with the Reservation database
 */
@Service
public class ReservationService {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private RoomService roomService;
    @Autowired
    private Invoice invoice;

    /**
     * The method searches for all reservations in the database of Reservation
     * @return  list of Reservations
     */
    public List<Reservation> allReservation() {
        return reservationRepo.findAll();
    }

    /**
     * The method searches for all reservations of a given user in the database of Reservation
     * @param username  name of user
     * @return  list of Reservations
     */
    public List<Reservation> findReservationByUser(String username) {
        return reservationRepo.findAllByUser(userService.findByUsername(username).getId());
    }

    /**
     * The method writes the data of the new reservation to the Reservation database
     * @param form  reservation form
     * @return  Reservation
     */
    public Reservation saveReservation(Reservation form) {
        form.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        Room room = roomService.findByNumber(form.getNumber());
        form.setRoom(room.getType());
        double serviceAmount;
        form.getArrival().isBefore(LocalDate.now());
        if (form.getServices().getType().equals("стоянка")) serviceAmount = form.getServices().getPrice() * form.getDays();
        else serviceAmount = form.getServices().getPrice();
        form.setAmount((room.getPrice() + form.getFood().getPrice() * form.getPersons()) * form.getDays() + serviceAmount);
        reservationRepo.save(form);
        return form;
    }

    /**
     * The method replaces a room in a specific reservation with a specified room
     * @param reservationId  reservation id
     * @param roomId  room id
     */
    public void changeRoom(int reservationId, int roomId) {
        if (reservationRepo.findById(reservationId).isPresent()) {
            Reservation reservation = reservationRepo.findById(reservationId).get();
            reservation.setNumber(roomId);
            saveReservation(reservation);
        }
    }

    /**
     * The method changes the reservation status to the specified one.
     * If the specified status is "Invoice", then it calls the "createInvoice" method and the "sendMailWithAttachment" method.
     * If the specified status is "CLOSED", then the "deleteInvoice" method is called.
     * @param reservationId  reservation id
     * @param statusReservation  status Reservation
     * @throws MessagingException if the message cannot be created
     * @throws IOException if the invoice cannot be created
     */
    public void changeStatusReservation(int reservationId, String statusReservation) throws MessagingException, IOException {
        if (reservationRepo.findById(reservationId).isPresent()) {
            Reservation reservation = reservationRepo.findById(reservationId).get();
            reservation.setStatus(statusReservation);
            String fileName = reservationId + ".pdf";
            String filePath = "src/main/resources/static/" + fileName;
            if (statusReservation.equals("СЧЕТ")) {
                invoice.createInvoice(reservationRepo.findById(reservationId).get(), filePath,
                        roomService.findByNumber(reservation.getNumber()).getPrice());
                mailSender.sendMailWithAttachment(userService.findUserById(reservation.getUser()).getEmail(), fileName,
                        "Счет во вложении", filePath);
            }
            if (statusReservation.equals("ЗАКРЫТ")) invoice.deleteInvoice(filePath);
            reservationRepo.save(reservation);
        }
    }

    /**
     * The method deletes the reservation data to the Reservation database and calls the "changeStatusReservation" method
     * @param reservationId  reservation id
     * @throws MessagingException if the message cannot be created
     * @throws IOException if the invoice cannot be created
     */
    public void deleteReservation(int reservationId) throws MessagingException, IOException {
        if (reservationRepo.findById(reservationId).isPresent()) {
            changeStatusReservation(reservationId, "ЗАКРЫТ");
            reservationRepo.deleteById(reservationId);
        }
    }
}
