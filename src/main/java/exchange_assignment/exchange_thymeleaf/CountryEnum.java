package exchange_assignment.exchange_thymeleaf;

public enum CountryEnum {
    KRW("한국")
    , PHP("필리핀")
    , JPY("일본");

    private final String description;

    CountryEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
