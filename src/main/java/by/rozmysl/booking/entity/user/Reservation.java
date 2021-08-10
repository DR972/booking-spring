package by.rozmysl.booking.entity.user;

import by.rozmysl.booking.entity.hotel.AdditionalServices;
import by.rozmysl.booking.entity.hotel.Food;
import by.rozmysl.booking.entity.hotel.StatusReservation;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
/**
 * The class is used to store Reservation objects with the <b>id</b>, <b>number</b>, <b>user</b>, <b>room</b>, <b>persons</b>,
 * <b>food</b>, <b>arrival</b>, <b>departure</b>, <b>days</b>, <b>services</b>, <b>amount</b>, <b>status</b> properties
 */
@Data
@Entity
@Table(name = "бронирования")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @GenericGenerator(name = "seq", strategy = "increment")
    private int id;
    private int number;
    private long user;
    private String room;
    @Min(1)
    private int persons;
    @ManyToOne(fetch = FetchType.EAGER)
    private Food food;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrival;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departure;
    @Min(1)
    private int days;
    @ManyToOne(fetch = FetchType.EAGER)
    private AdditionalServices services;
    private double amount;
    private String status = StatusReservation.ORDER.getStatus();
}
