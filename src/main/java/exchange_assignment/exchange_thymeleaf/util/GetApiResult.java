package exchange_assignment.exchange_thymeleaf.util;

import exchange_assignment.exchange_thymeleaf.controller.form.ExchangeRequestForm;
import exchange_assignment.exchange_thymeleaf.domain.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GetApiResult {

    private final RestTemplate restTemplate;
    @Value("${requestURL}")
    private String requestUrl;
    private final DecimalFormat form = new DecimalFormat("###,###.00");

    public String getExchangeInfo(String country){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiResponse apiResponse = restTemplate.getForObject(requestUrl, ApiResponse.class);
        Double result = Double.valueOf(apiResponse.getQuotes().get(country));

        return form.format(result);
    }

    public String getReceivable(ExchangeRequestForm requestDto) throws java.text.ParseException {

        double exchangeRate = (double) form.parse(requestDto.getExchangeRate());
        int sendPrice = requestDto.getSendPrice();

        return form.format(exchangeRate * sendPrice);
    }
}
