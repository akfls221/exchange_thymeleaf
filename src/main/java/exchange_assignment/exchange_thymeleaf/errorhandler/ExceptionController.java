package exchange_assignment.exchange_thymeleaf.errorhandler;

import exchange_assignment.exchange_thymeleaf.errorhandler.exception.ApiCallException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApiCallException.class)
    private ErrorResponse apiCallExceptionAdvice(ApiCallException e) {
        return ErrorResponse.builder()
                .code(e.getERROR_CODE())
                .description(e.getMessage())
                .build();
    }
}
