package bo.felipe.app.service;

import bo.felipe.app.client.IDatabaseC;
import bo.felipe.app.client.IWebpayClient;
import bo.felipe.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
    @Value("${api.key.id.capture}")
    String apiKeyIdCapture;

    // Crear una transacción
    public VentaResponse addVenta(VentaRequest venta){

        return iWebpayClient.addVenta(apiKeyId, apiKeySecret, venta);
    }

    // Confirmar una transacción
    public StatusResponse confirmVenta(String token_ws){
        StatusResponse statusVenta = iWebpayClient.confirmVenta(apiKeyId, apiKeySecret, token_ws);
        Venta venta = iDatabaseC.getVentaByBO(statusVenta.getBuyOrder());

        venta.setStatus(statusVenta.getStatus());
        venta.setAuthorizationCode(statusVenta.getAuthorizationCode());

        iDatabaseC.updateStatusVenta(statusVenta.getBuyOrder(), venta);
        return statusVenta;
    }

    // Obtener estado de una transacción
    public StatusResponse getStatusVenta(String buy_order){

        Venta venta = iDatabaseC.getVentaByBO(buy_order);
        return iWebpayClient.getStatus(apiKeyId, apiKeySecret, venta.getToken());
    }

    // Reversar o Anular un pago
    public RefundResponse refundVenta(String token, RefundRequest refund){
        return iWebpayClient.refundVenta(apiKeyId, apiKeySecret, token, refund);
    }


    // Capturar una transacción
    public CaptureResponse captureResponse(String token, CaptureRequest captureRequest){
        return iWebpayClient.captureVenta(apiKeyIdCapture, apiKeySecret, token, captureRequest);
    }

}
