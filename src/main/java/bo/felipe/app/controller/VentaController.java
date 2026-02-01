package bo.felipe.app.controller;

import bo.felipe.app.model.*;
import bo.felipe.app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class VentaController {

    @Autowired
    VentaService ventaService;

    // Crear una transacción
    @PostMapping("/add/venta")
    public VentaResponse addVenta(@RequestBody VentaRequest venta){

        System.out.println(ventaService.addVenta(venta));

        return ventaService.addVenta(venta);
    }

    // Confirmar una transacción
    @PutMapping("/confirm/venta/{token_ws}")
    public StatusResponse confirmVenta(@PathVariable("token_ws") String token_ws){

        System.out.println(ventaService.confirmVenta(token_ws));

        return ventaService.confirmVenta(token_ws);
    }

    // Obtener estado de una transacción
    @GetMapping("/status/venta/{buy_order}")
    public StatusResponse statusVenta(@PathVariable("buy_order") String buy_order){
        return ventaService.getStatusVenta(buy_order);
    }

    // Reversar o Anular un pago
    @PostMapping("/refund/venta/{token}")
    public RefundResponse refundVenta(@PathVariable("token")String token, @RequestBody RefundRequest refund){
        return ventaService.refundVenta(token, refund);
    }

    // Capturar una transacción
    @PutMapping("/capture/venta/{token}")
    public CaptureResponse captureResponse(@PathVariable("token") String token, @RequestBody CaptureRequest captureRequest){
        return ventaService.captureResponse(token, captureRequest);
    }

}