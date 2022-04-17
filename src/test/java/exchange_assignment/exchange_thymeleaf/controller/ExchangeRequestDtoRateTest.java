package exchange_assignment.exchange_thymeleaf.controller;

import exchange_assignment.exchange_thymeleaf.controller.form.ExchangeRequestForm;
import exchange_assignment.exchange_thymeleaf.service.ExchangeRateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeRateController.class)
class ExchangeRequestDtoRateTest {

    @MockBean
    ExchangeRateService exchangeService;

    @MockBean
    RestTemplate restTemplate;

    @Mock
    private BindingResult mockBindingResult;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("메인화면 접속 테스트")
    @Test
    public void 메인화면() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange"))
                .andExpect(status().isOk());
    }

    @DisplayName("SUBMIT POST 테스트(정상)")
    @Test
    public void POST_Controller() throws Exception {
        ExchangeRequestForm requestForm = new ExchangeRequestForm("KRW", "1,121.41", 100);

        mockMvc.perform(MockMvcRequestBuilders.post("/exchange")
                        .param("country", requestForm.getCountry())
                        .param("exchangeRate", requestForm.getExchangeRate())
                        .param("sendPrice", String.valueOf(requestForm.getSendPrice()))
                )
                .andExpect(MockMvcResultMatchers.model().attributeExists("exchange"))
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.view().name("exchangeResultForm"))
                .andExpect(status().isOk());
    }

    @DisplayName("API GET POST 테스트(정상)")
    @Test
    public void API_GET_Controller() throws Exception {
        String country = "KRW";

        mockMvc.perform(MockMvcRequestBuilders.post("/getExchangeInfo/" + country))
                .andExpect(status().isOk());

    }

    @DisplayName("SUBMIT POST 테스트(실패조건)_송금액 범위초과")
    @Test
    public void POST_Controller_범위초과() throws Exception {
        ExchangeRequestForm requestForm = new ExchangeRequestForm("KRW", "1,121.41", 10000000);

        mockMvc.perform(MockMvcRequestBuilders.post("/exchange")
                        .param("country", requestForm.getCountry())
                        .param("exchangeRate", requestForm.getExchangeRate())
                        .param("sendPrice", String.valueOf(requestForm.getSendPrice()))
                )
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().errorCount(1))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("exchange"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("exchange", "sendPrice", "Range"));
    }

    @DisplayName("SUBMIT POST 테스트(실패조건)_송금액 범위미달")
    @Test
    public void POST_Controller_범위미달() throws Exception {
        ExchangeRequestForm requestForm = new ExchangeRequestForm("KRW", "1,121.41", -1);

        mockMvc.perform(MockMvcRequestBuilders.post("/exchange")
                        .param("country", requestForm.getCountry())
                        .param("exchangeRate", requestForm.getExchangeRate())
                        .param("sendPrice", String.valueOf(requestForm.getSendPrice()))
                )
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().errorCount(1))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("exchange"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("exchange", "sendPrice", "Range"));
    }

    @DisplayName("SUBMIT POST 테스트(실패조건)_송금액 typeMismatch")
    @Test
    public void POST_Controller_Null입력() throws Exception {
        ExchangeRequestForm requestForm = new ExchangeRequestForm("KRW", "1,121.41", null);

        mockMvc.perform(MockMvcRequestBuilders.post("/exchange")
                        .param("country", requestForm.getCountry())
                        .param("exchangeRate", requestForm.getExchangeRate())
                        .param("sendPrice", String.valueOf(requestForm.getSendPrice()))
                )
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().errorCount(1))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("exchange"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("exchange", "sendPrice", "typeMismatch"));
    }

    @DisplayName("SUBMIT POST 테스트(실패조건)_송금액 typeMismatch2")
    @Test
    public void POST_Controller_문자입력() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/exchange")
                        .param("country", "KRW")
                        .param("exchangeRate", "1,121.41")
                        .param("sendPrice", "String INPUT")
                )
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().errorCount(1))
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("exchange"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("exchange", "sendPrice", "typeMismatch"));
    }

}