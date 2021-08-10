package by.rozmysl.booking.service.hotelService;

import by.rozmysl.booking.entity.user.Reservation;
import by.rozmysl.booking.entity.hotel.Room;
import by.rozmysl.booking.repository.userRepo.ReservationRepo;
import by.rozmysl.booking.repository.hotelRepo.RoomRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
/**
 * This class contains all the business logic for working with the Room database
 */
@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private ReservationRepo reservationRepo;

    /**
     * The method searches for all rooms in the database of Room
     * @return  list of Rooms
     */
    public List<Room> allRooms() {
        return roomRepo.findAll();
    }

    /**
     * The method searches for a room in the Room database by number
     * @param number  room number
     * @return  room
     */
    public Room findByNumber(int number) {
        return roomRepo.findByNumber(number);
    }

    /**
     * The method searches for all types of room in the database of Room
     * @return  list of Rooms
     */
    public List<Room> findAllTypeRooms() {
        return findRoomsByTypes(roomRepo.findAll());
    }

    /**
     * The method searches for all types of free rooms in the Room database corresponding to the reservation form
     * @param form  reservation form
     * @return  list of Rooms
     */
    public List<Room> findFreeRoomsByTypes(Reservation form) {
        return findRoomsByTypes(findFreeRooms(form));
    }

    /**
     * The method searches for all free rooms in the Room database corresponding to the reservation form
     * @param form  reservation form
     * @return  list of Rooms
     */
    public List<Room> findFreeRooms(Reservation form) {
        List<Room> occupiedRooms = reservationRepo.findAllByArrivalLessThanEqualAndDepartureGreaterThanEqual
                (form.getDeparture(), form.getArrival().plusDays(1))
                .stream().map(r -> roomRepo.findByNumber(r.getNumber())).collect(Collectors.toList());
        return roomRepo.findAll().stream().filter(i -> !occupiedRooms.contains(i)).collect(Collectors.toList());
    }

    /**
     * The method changes the price of all rooms of the same type and capacity in the Room database to a new one
     * @param roomId  room id
     * @param price  room price
     */
    public void changeRoomPrice(int roomId, double price) {
        Room room = roomRepo.findByNumber(roomId);
        allRooms().stream().filter(r -> r.getType().equals(room.getType()) && r.getSleeps() == room.getSleeps())
                .forEach(r -> {
                    r.setPrice(price);
                    roomRepo.save(r);
                });
    }

    /**
     * The method changes the room parameters in the Room database to new ones
     * @param roomId  room id
     * @param parameters  room parameters
     */
    public void changeRoomParameters(int roomId, int parameters) {
        Room param = roomRepo.findByNumber(parameters);
        Room room = roomRepo.findByNumber(roomId);
        room.setType(param.getType());
        room.setSleeps(param.getSleeps());
        room.setPrice(param.getPrice());
        roomRepo.save(room);
    }

//    /**
//     * The method changes the room parameter sleeps in the Room database to new ones
//     * @param roomId  room id
//     * @param sleeps  room sleeps
//     */
//    public void changeRoomSleeps(int roomId, int sleeps) {
//        Room room = roomRepo.findByNumber(roomId);
//        room.setSleeps(sleeps);
//        roomRepo.save(room);
//    }

    /**
     * The method makes a specification of rooms by type and capacity from a given list of rooms
     * @param rooms  list of Rooms
     * @return  list of Rooms
     */
    public List<Room> findRoomsByTypes(List<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++)
            for (int j = i + 1; j < rooms.size(); j++)
                if (rooms.get(j).getType().equals(rooms.get(i).getType()) && rooms.get(j).getSleeps() == rooms.get(i).getSleeps()) {
                    rooms.remove(j--);
                }
        return rooms;
    }

    /**
     * The method writes the data of the new room to the Room database
     * @param room  room
     */
    public void saveRoom(Room room) {
        roomRepo.save(room);
    }

    /**
     * The method checks the existence of a room of a given type and capacity in the Room database
     * @param type  type of room
     * @param sleeps  room sleeps
     * @return  the result of the truth check about the existence of the room
     */
    public boolean checkRoomByTypeAndSleeps(String type, int sleeps) {
        return allRooms().stream().noneMatch(r -> r.getType().equals(type) && r.getSleeps() == sleeps);
    }

    /**
     * The method finds the price of a room of a given type and capacity in the Room database
     * @param type  type or room
     * @param sleeps  room sleeps
     * @return  room
     */
    public Room findRoomByTypeAndSleeps(String type, int sleeps) {
        return allRooms().stream().filter(r -> r.getType().equals(type) && r.getSleeps() == sleeps).findFirst().get();
    }

    /**
     * The method checks the existence of a room of a given type and capacity in the Room database
     * @param form  reservation form
     * @return  the result of the truth check about the existence of the room
     */
    public boolean checkFreeRoomByTypeAndSleeps(Reservation form) {
        return findFreeRoomsByTypes(form).stream()
                .noneMatch(r -> r.getType().equals(form.getRoom()) && r.getSleeps() == form.getPersons());
    }

    /**
     * The method finds the price of a room of a given type and capacity in the Room database
     * @param form  reservation form
     * @return  room
     */
    public Room findFreeRoomByTypeAndSleeps(Reservation form) {
        return findFreeRoomsByTypes(form).stream()
                .filter(r -> r.getType().equals(form.getRoom()) && r.getSleeps() == form.getPersons()).findFirst().get();
    }

    /**
     * The method deletes the room data to the Room database
     * @param roomId  room id
     */
    public void deleteRoom(int roomId) {
        if (roomRepo.findById(roomId).isPresent()) roomRepo.deleteById(roomId);
    }
}
