package bo.felipe.app.client;

import bo.felipe.app.model.StatusResponse;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "svc-webpay", url = "https://webpay3gint.transbank.cl")
public interface IWebpayClient {

    // Crear una transacción
    @PostMapping(path = "/rswebpaytransaction/api/webpay/v1.2/transactions")
    VentaResponse addVenta(@RequestHeader("Tbk-Api-Key-Id") String apiKeyId,
                           @RequestHeader("Tbk-Api-Key-Secret") String apiKeySecret,
                           @RequestBody VentaRequest venta);

    // Confirmar una transacción
    @PutMapping(path = "/rswebpaytransaction/api/webpay/v1.2/transactions/{token}")
    StatusResponse confirmVenta(@RequestHeader("Tbk-Api-Key-Id") String apiKeyId,
                             @RequestHeader("Tbk-Api-Key-Secret") String apiKeySecret,
                             @PathVariable String token);

    // Obtener estado de una transacción
    @PostMapping(path = "/rswebpaytransaction/api/webpay/v1.2/transactions/{token}")
    StatusResponse getStatus(@RequestHeader("Tbk-Api-Key-Id") String apiKeyId,
                             @RequestHeader("Tbk-Api-Key-Secret") String apiKeySecret,
                             @PathVariable String token);

    // Reversar o Anular un pago

    // Capturar una transacción

}