package com.fatihdurdu.inventoryservice;

import com.fatihdurdu.inventoryservice.model.Inventory;
import com.fatihdurdu.inventoryservice.repository.InventoryRepository;
import com.netflix.discovery.EurekaNamespace;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(InventoryRepository inventoryRepository){
		return  args -> {
			Inventory inventory=new Inventory();
			inventory.setQuantity(100);
			inventory.setSkuCode("iphone_13");
			inventoryRepository.save(inventory);

			Inventory inventory1=new Inventory();
			inventory1.setQuantity(0);
			inventory1.setSkuCode("iphone_13_red");
			inventoryRepository.save(inventory1);
		};

	}

}
