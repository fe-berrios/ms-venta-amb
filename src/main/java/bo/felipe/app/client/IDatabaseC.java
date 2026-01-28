package bo.felipe.app.client;

import bo.felipe.app.model.VentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "svc-database", url = "http://localhost:8083")
public interface IDatabaseC {

    @GetMapping("/db/venta/{id}")
    VentaResponse getVenta(@PathVariable("id")Long id);

}
