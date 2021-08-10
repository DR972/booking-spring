package by.rozmysl.booking.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
/**
 * The class is used to store Api Error objects with the <b>message</b>, <b>debugMessage</b>, <b>errors</b> properties
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private String debugMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    /**
     *  The constructor creates a new object ApiError
     * @param message  message
     * @param debugMessage  debugging message
     */
    public ApiError(String message, String debugMessage){
        this.message=message;
        this.debugMessage=debugMessage;
    }
}
