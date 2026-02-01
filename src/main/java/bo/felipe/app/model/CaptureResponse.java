package bo.felipe.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CaptureResponse {

    @JsonProperty("token")
    private String token;
    @JsonProperty("authorization_code")
    private String authorizationCode;
    @JsonProperty("authorization_date")
    private String authorizationDate;
    @JsonProperty("captured_amount")
    private String capturedAmount;
    @JsonProperty("response_code")
    private String responseCode;

}
