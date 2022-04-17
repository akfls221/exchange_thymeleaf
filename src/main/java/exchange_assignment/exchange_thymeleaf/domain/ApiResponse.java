package exchange_assignment.exchange_thymeleaf.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ApiResponse {
    private boolean success;
    private String terms;
    private String privacy;
    private Integer timestamp;
    private String source;
    private Map<String, String> quotes;

    public ApiResponse(boolean success, String terms, String privacy, Integer timestamp, String source, Map<String, String> quotes) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.timestamp = timestamp;
        this.source = source;
        this.quotes = quotes;
    }

    public ApiResponse() {
    }
}
