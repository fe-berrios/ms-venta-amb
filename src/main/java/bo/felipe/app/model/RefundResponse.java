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
public class RefundResponse {

    @JsonProperty("type")
    private String type;
    @JsonProperty("authorization_code")
    private String authorizationCode;
    @JsonProperty("authorization_date")
    private String authorizationDate;
    @JsonProperty("nullified_amount")
    private Integer nullifiedAmount;
    @JsonProperty("balance")
    private Integer balance;
    @JsonProperty("response_code")
    private Integer responseCode;

}
