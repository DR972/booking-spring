package by.rozmysl.booking.entity.hotel;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * The class is used to store Food objects with the <b>type</b> and <b>price</b> properties
 */
@Data
@Entity
@Table(name = "питание")
public class Food {
    @Id
    private String type;
    private double price;
}


