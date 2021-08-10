package by.rozmysl.booking.entity.hotel;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
/**
 * The class is used to store Additional Services objects with the <b>number</b>, <b>sleeps</b>, <b>price</b>, <b>type</b> properties
 */
@Data
@Entity
@Table(name = "комнаты")
public class Room {
    @Id
    @Min(value = 1, message = "Число должно быть больше 1")
    private int number;
    @Min(value = 1, message = "Число должно быть больше 1")
    private int sleeps;
    private double price;
    @NotEmpty( message = "Надо выбрать тип")
    private String type;
}
