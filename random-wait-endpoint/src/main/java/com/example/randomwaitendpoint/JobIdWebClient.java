package com.example.randomwaitendpoint;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;


public class JobIdWebClient {

	private WebClient client = WebClient.create("http://localhost:3000/randomWaitEndpoint");	

	public Mono<Job> getJob(int id) {		
		return client.get()
				.uri("?id={id}", id)			
				.retrieve()
				.bodyToMono(Job.class);		 
	}	
	
	public Flux<Job> fetchJobs(List<Integer> jobIds) {
		return Flux.fromIterable(jobIds)
				.parallel()				
				.runOn(Schedulers.elastic())
				.flatMap(this::getJob)
				.sequential();				
	}
}

