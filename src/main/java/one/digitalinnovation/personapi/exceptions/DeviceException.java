package one.digitalinnovation.personapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class DeviceException extends Throwable {
    public DeviceException(String s) {
        super(s);
    }
}
