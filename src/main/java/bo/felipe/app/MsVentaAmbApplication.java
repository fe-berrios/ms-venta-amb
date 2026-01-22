package bo.felipe.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsVentaAmbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsVentaAmbApplication.class, args);
	}

}
