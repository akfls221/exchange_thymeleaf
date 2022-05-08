package exchange_assignment.exchange_thymeleaf.errorhandler;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private String code;

    private String description;

    private String detail;

    @Builder
    public ErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
