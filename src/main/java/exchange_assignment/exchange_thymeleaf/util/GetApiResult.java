package exchange_assignment.exchange_thymeleaf.util;

import exchange_assignment.exchange_thymeleaf.controller.form.ExchangeRequestForm;
import exchange_assignment.exchange_thymeleaf.domain.ApiResponse;
import exchange_assignment.exchange_thymeleaf.domain.Exchange;
import exchange_assignment.exchange_thymeleaf.errorhandler.ErrorCode;
import exchange_assignment.exchange_thymeleaf.errorhandler.RestTemplateResponseErrorHandler;
import exchange_assignment.exchange_thymeleaf.errorhandler.exception.ApiCallException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@Component
@RequiredArgsConstructor
public class GetApiResult {

    private final RestTemplate restTemplate;
    @Value("${requestURL}")
    private String requestUrl;
    private final DecimalFormat form = new DecimalFormat("###,###.00");

    @Autowired
    public GetApiResult(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public Exchange getExchangeInfo(String country){
        System.out.println("country = " + country);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiResponse apiResponse = restTemplate.getForObject(requestUrl, ApiResponse.class);
        if (apiResponse.getQuotes() == null) {
            throw new ApiCallException(ErrorCode.CLIENT_ERROR.getDescription(), ErrorCode.CLIENT_ERROR.getCode());
        }
        Double result = Double.valueOf(apiResponse.getQuotes().get(country));

        return Exchange.builder()
                .exchangeRate(form.format((result)))
                .build();
    }

    public String getReceivable(ExchangeRequestForm requestDto) throws java.text.ParseException {

        double exchangeRate = (double) form.parse(requestDto.getExchangeRate());
        int sendPrice = requestDto.getSendPrice();

        return form.format(exchangeRate * sendPrice);
    }
}
