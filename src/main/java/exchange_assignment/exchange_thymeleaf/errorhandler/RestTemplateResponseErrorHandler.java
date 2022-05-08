package exchange_assignment.exchange_thymeleaf.errorhandler;

import exchange_assignment.exchange_thymeleaf.errorhandler.exception.ApiCallException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        return (
                response.getStatusCode().series() == CLIENT_ERROR
                || response.getStatusCode().series() == SERVER_ERROR
        );
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode().series() == SERVER_ERROR) {
            throw new ApiCallException(ErrorCode.SERVER_ERROR.getDescription(), ErrorCode.SERVER_ERROR.getCode());
        } else if (response.getStatusCode().series() == CLIENT_ERROR) {
            throw new ApiCallException(ErrorCode.CLIENT_ERROR.getDescription(), ErrorCode.CLIENT_ERROR.getCode());
        }

    }
}
