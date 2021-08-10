package by.rozmysl.booking.entity.hotel;
/**
 * The class is used to store Status Reservation objects with the <b>status</b> properties
 */
public enum StatusReservation {
    ORDER("ЗАКАЗ"),
    INVOICE("СЧЕТ"),
    PAID("ОПЛАЧЕН"),
    CLOSED("ЗАКРЫТ");

    private String status;

    /**
     * The constructor creates a new object Status Reservation
     * @param status  status reservation
     */
    StatusReservation(String status) {
        this.status = status;
    }

    /**
     * The method gets the value of the status property
     * @return  property value status reservation
     */
    public String getStatus() {
        return status;
    }

    /**
     * The method sets the value of the status property
     * @param status  status reservation
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
