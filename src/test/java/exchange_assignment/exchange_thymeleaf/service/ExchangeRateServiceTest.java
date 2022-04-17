package exchange_assignment.exchange_thymeleaf.service;

import exchange_assignment.exchange_thymeleaf.controller.form.ExchangeRequestForm;
import exchange_assignment.exchange_thymeleaf.util.GetApiResult;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.ParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ExchangeRateServiceTest {

    @Autowired
    ExchangeRateService exchangeRateService;


    @Test
    void getExchangeResult() throws ParseException {
        ExchangeRequestForm requestForm = new ExchangeRequestForm("KRW", "1121.41", 100);
        String exchangeResult = exchangeRateService.getExchangeResult(requestForm);
        assertThat(exchangeResult).isEqualTo("112,141.00");
    }
}