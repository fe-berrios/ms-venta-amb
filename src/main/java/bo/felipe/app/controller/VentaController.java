package bo.felipe.app.controller;

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

    @PutMapping("/status/venta/{id}")
    public StatusResponse confirmVenta(@PathVariable("id") Long id){
        return ventaService.confirmVenta(id);
    }

}
