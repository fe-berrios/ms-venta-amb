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
public class CaptureRequest {

    @JsonProperty("buy_order")
    private String buy_order;
    @JsonProperty("authorization_code")
    private String authorizationCode;
    @JsonProperty("capture_amount")
    private Integer captureAmount;

}
