package exchange_assignment.exchange_thymeleaf.controller.form;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DataBindingException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExchangeRequestFormTest {

    @Test
    public void 롬복기능Test() {
        ExchangeRequestForm requestForm = new ExchangeRequestForm("KRW", "1,121.41", 100);

        assertThat(requestForm.getCountry()).isEqualTo("KRW");
        assertThat(requestForm.getExchangeRate()).isEqualTo("1,121.41");
        assertThat(requestForm.getSendPrice()).isEqualTo(100);
    }

    @Test
    public void 롬복기능Test2() {
        ExchangeRequestForm requestForm = new ExchangeRequestForm("KRW", "1,121.41", 100000);

        assertThat(requestForm.getCountry()).isEqualTo("KRW");
        assertThat(requestForm.getExchangeRate()).isEqualTo("1,121.41");
        assertThat(requestForm.getSendPrice()).isEqualTo(100000);
    }

    @Test
    public void 롬복기능Test3() {
        ExchangeRequestForm requestForm = new ExchangeRequestForm("KRW", "1,121.41", -1);

        assertThat(requestForm.getCountry()).isEqualTo("KRW");
        assertThat(requestForm.getExchangeRate()).isEqualTo("1,121.41");
        assertThat(requestForm.getSendPrice()).isEqualTo(-1);
    }

}