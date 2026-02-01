package bo.felipe.app.controller;

import bo.felipe.app.model.CaptureResponse;
import bo.felipe.app.model.StatusResponse;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import bo.felipe.app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @PostMapping("/add/venta")
    public VentaResponse addVenta(@RequestBody VentaRequest venta){

        System.out.println(ventaService.addVenta(venta));

        return ventaService.addVenta(venta);
    }

    @PutMapping("/confirm/venta/{token_ws}")
    public StatusResponse confirmVenta(@PathVariable("token_ws") String token_ws){

        System.out.println(ventaService.confirmVenta(token_ws));

        return ventaService.confirmVenta(token_ws);
    }

    @GetMapping("/status/venta/{buy_order}")
    public StatusResponse statusVenta(@PathVariable("buy_order") String buy_order){
        return ventaService.getStatusVenta(buy_order);
    }

    @PutMapping("/capture/venta/{token}")
    public CaptureResponse captureResponse(@PathVariable("token") String token){
        return ventaService.captureResponse(token);
    }

}
