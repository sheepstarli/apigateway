package org.chenxing.apigateway;

import org.chenxing.apigateway.core.ribbon.KceRibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@EnableDiscoveryClient
@EnableZuulServer
@SpringBootApplication(exclude = {KceRibbonConfiguration.class})
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}
}
