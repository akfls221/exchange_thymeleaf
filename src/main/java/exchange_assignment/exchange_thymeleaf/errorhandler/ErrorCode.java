package exchange_assignment.exchange_thymeleaf.errorhandler;

import lombok.Getter;
import lombok.ToString;

@ToString
public enum ErrorCode {

    CLIENT_ERROR("ERROR_CODE_400","잘못된 통신 방식입니다.")
    , SERVER_ERROR("ERROR_CODE_500","서버로 부터 응답을 받아오는데 실패 했습니다.");

    @Getter
    private String code;

    @Getter
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
