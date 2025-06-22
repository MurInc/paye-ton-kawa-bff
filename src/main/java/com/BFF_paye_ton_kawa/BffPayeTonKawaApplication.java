package com.BFF_paye_ton_kawa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(exclude = {
		org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.metrics.export.otlp.OtlpMetricsExportAutoConfiguration.class
})
@RequestMapping("/api")
public class BffPayeTonKawaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffPayeTonKawaApplication.class, args);
	}

}
