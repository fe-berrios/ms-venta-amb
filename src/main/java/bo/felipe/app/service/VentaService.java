package bo.felipe.app.service;

import bo.felipe.app.client.IWebpayClient;
import bo.felipe.app.model.StatusResponse;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    IWebpayClient iWebpayClient;

    public VentaResponse addVenta(VentaRequest venta){

        String apiKeyId = "597055555532";
        String apiKeySecret = "579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C";

        return iWebpayClient.addVenta(apiKeyId, apiKeySecret, venta);
    }

    public StatusResponse getStatus(){

        String apiKeyId = "597055555532";
        String apiKeySecret = "579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C";

        return iWebpayClient.getStatus(apiKeyId, apiKeySecret, "token");
    }

}
