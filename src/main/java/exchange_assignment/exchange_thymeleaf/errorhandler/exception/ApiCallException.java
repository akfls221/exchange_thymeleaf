package exchange_assignment.exchange_thymeleaf.errorhandler.exception;

import exchange_assignment.exchange_thymeleaf.errorhandler.ErrorCode;
import lombok.Getter;

@Getter
public class ApiCallException extends RuntimeException {

    private final String ERROR_CODE;

    public ApiCallException(String message, String code) {
        super(message);
        this.ERROR_CODE = code;
    }
}
