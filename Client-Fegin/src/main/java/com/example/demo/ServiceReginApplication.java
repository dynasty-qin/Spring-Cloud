package com.example.demo;

import com.example.demo.service.SchedualServiceHi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ServiceReginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceReginApplication.class, args);
	}
}
