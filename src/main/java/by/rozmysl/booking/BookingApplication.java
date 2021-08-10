
package by.rozmysl.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * This is my main class, it runs the application
 * @author Dzmitry Rozmysl.
 * @version 1.1
 */
@SpringBootApplication
public class BookingApplication {
	/**
	 * This is the main method, it launches the application
	 * @param args  command line parameters
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}
}
