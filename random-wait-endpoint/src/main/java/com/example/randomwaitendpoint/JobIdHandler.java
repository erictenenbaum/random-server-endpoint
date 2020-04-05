package com.example.randomwaitendpoint;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;


import reactor.core.publisher.Mono;

@Component
public class JobIdHandler {
	
	public Mono<ServerResponse> getJobIdFromServer(ServerRequest request) {	
		System.out.println(request.queryParam("id").get());		
		
		UUID jobId = UUID.randomUUID();
		Job job = new Job();		
		job.setJobId(jobId + "");
		
		try {
			Thread.sleep(RandomWaitEndpointApplication.randomInt(1000, 10000));			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(job));
	}
}