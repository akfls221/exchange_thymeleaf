package exchange_assignment.exchange_thymeleaf.controller.form;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class ExchangeRequestForm {

    //국가
    @NotNull(message = "수취국가 선택은 필수 입니다.")
    private final String country;

    private final String exchangeRate;

    //송금액
    @NotNull(message = "송금액이 바르지 않습니다.")
    @Range(min= 0, max=10000, message = "송금액이 바르지 않습니다")
    private final Integer sendPrice;

    public ExchangeRequestForm(String country, String exchangeRate, Integer sendPrice) {
        this.country = country;
        this.exchangeRate = exchangeRate;
        this.sendPrice = sendPrice;
    }
}
