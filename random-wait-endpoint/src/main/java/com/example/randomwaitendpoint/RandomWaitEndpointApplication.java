package com.example.randomwaitendpoint;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

import java.io.File;


@EnableAsync
@SpringBootApplication
@EnableWebFlux
public class RandomWaitEndpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomWaitEndpointApplication.class, args);			
		
		int randomNumber = randomInt(1000, 2000);
		System.out.println("Number of requests: " + randomNumber);
		
		Map<String, List<String>> jobMap = new HashMap<String, List<String>>();
		jobMap.put("jobs", new ArrayList<String>());

		List<Integer> jobIds = new ArrayList<Integer>();		
		for(int i = 1; i < randomNumber + 1; i++) {
			jobIds.add(i);
		}
		
		JobIdWebClient jobIdWebClient = new JobIdWebClient();
		Flux<Job> fluxJobs = jobIdWebClient.fetchJobs(jobIds);
		
		fluxJobs
			.log()
			.subscribe(new Subscriber<Job>() {
				private Subscription s;
			    private int onNextAmount;
			    
				@Override
				public void onSubscribe(Subscription s) {
					this.s = s;
					s.request(2);	
				}

				@Override
				public void onNext(Job job) {
					jobMap.get("jobs").add(job.getJobId());
					
					onNextAmount++;
			        if (onNextAmount % 2 == 0) {
			            s.request(2);
			        }					
				}

				@Override
				public void onError(Throwable t) {					
					throw new Error(t);
				}

				@Override
				public void onComplete() {
					ObjectMapper mapper = new ObjectMapper();
					
					try {
						System.out.println(jobMap);
						mapper.writeValue(new File("./jobIds.json"), jobMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}				
			});			
	}
	
	public static int randomInt(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}
}
