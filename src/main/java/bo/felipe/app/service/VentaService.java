package bo.felipe.app.service;

import bo.felipe.app.client.IDatabaseC;
import bo.felipe.app.client.IWebpayClient;
import bo.felipe.app.model.StatusResponse;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    IWebpayClient iWebpayClient;

    @Autowired
    IDatabaseC iDatabaseC;

    @Value("${api.key.secret}")
    String apiKeySecret;
    @Value("${api.key.id}")
    String apiKeyId;

    // Crear una transacción
    public VentaResponse addVenta(VentaRequest venta){

        return iWebpayClient.addVenta(apiKeyId, apiKeySecret, venta);
    }

    // Confirmar una transacción
    public StatusResponse confirmVenta(Long id){

        VentaResponse venta = iDatabaseC.getVenta(id);
        String token = venta.getToken();

        return iWebpayClient.confirmVenta(apiKeyId, apiKeySecret, token);
    }

    // Obtener estado de una transacción
    public StatusResponse getStatus(){

        String apiKeyId = "597055555532";
        String apiKeySecret = "579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C";

        return iWebpayClient.getStatus(apiKeyId, apiKeySecret, "token");
    }

    // Reversar o Anular un pago

    // Capturar una transacción

}
