package bo.felipe.app.client;

import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "svc-webpay", url = "https://webpay3gint.transbank.cl")
public interface IWebpayClient {

    @PostMapping(path = "/rswebpaytransaction/api/webpay/v1.2/transactions")
    VentaResponse addVenta(@RequestHeader("Tbk-Api-Key-Id") String apiKeyId,
                           @RequestHeader("Tbk-Api-Key-Secret") String apiKeySecret,
                           @RequestBody VentaRequest venta);

}