package bo.felipe.app.client;

import bo.felipe.app.model.Venta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "svc-database", url = "http://localhost:8083")
public interface IDatabaseC {

    @GetMapping("/db/venta/bo/{buy_order}")
    Venta getVentaByBO(@PathVariable("buy_order")String buy_order);

    @PutMapping("/db/update/venta/{buy_order}")
    Venta updateStatusVenta(@PathVariable("buy_order")String buy_order, @RequestBody Venta venta);

    @GetMapping("/db/venta/token/{token}")
    Venta getVentaByToken(@PathVariable("token")String token);

}
