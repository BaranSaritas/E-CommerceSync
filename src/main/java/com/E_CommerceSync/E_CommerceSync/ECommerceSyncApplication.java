package com.E_CommerceSync.E_CommerceSync;

import com.E_CommerceSync.E_CommerceSync.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class ECommerceSyncApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceSyncApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
