package com.kat.avropossimulator;

import com.kat.avropossimulator.config.TopicsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(TopicsProperties.class)
@EnableScheduling
public class AvroPosSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvroPosSimulatorApplication.class, args);
	}

}
