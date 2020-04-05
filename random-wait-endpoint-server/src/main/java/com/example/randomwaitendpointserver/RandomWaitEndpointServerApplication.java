package com.example.randomwaitendpointserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomWaitEndpointServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomWaitEndpointServerApplication.class, args);
	}
	
	public static int randomInt(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

}
