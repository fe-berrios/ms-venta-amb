package bo.felipe.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VentaRequest {

    @JsonProperty("buy_order")
    private String buyOrder;
    @JsonProperty("session_id")
    private String sessionId;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("return_url")
    private String returnUrl;

}
