package exchange_assignment.exchange_thymeleaf.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Exchange {

    //국가
    private String country;

    private String exchangeRate;

    //송금액
    private int sendPrice;
    
    private String receivable;

    @Builder
    public Exchange(String country, String exchangeRate, int sendPrice, String receivable) {
        this.country = country;
        this.exchangeRate = exchangeRate;
        this.sendPrice = sendPrice;
        this.receivable = receivable;
    }

    public Exchange() {
    }
}
