package exchange_assignment.exchange_thymeleaf.service;

import exchange_assignment.exchange_thymeleaf.controller.form.ExchangeRequestForm;
import exchange_assignment.exchange_thymeleaf.domain.Exchange;
import exchange_assignment.exchange_thymeleaf.util.GetApiResult;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExchangeRateService {

    private final GetApiResult getApiResult;

    public Exchange getExchangeInfo(String country){

        return getApiResult.getExchangeInfo(country);
    }

    public String getExchangeResult(ExchangeRequestForm requestDto) throws java.text.ParseException {

        return getApiResult.getReceivable(requestDto);
    }
}
