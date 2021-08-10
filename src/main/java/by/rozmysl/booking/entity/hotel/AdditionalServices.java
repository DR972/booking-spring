package by.rozmysl.booking.entity.hotel;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * The class is used to store Additional Services objects with the <b>type</b> and <b>price</b> properties
 */
@Data
@Entity
@Table(name = "доп.услуги")
public class AdditionalServices {
    @Id
    private String type;
    private double price;
}
