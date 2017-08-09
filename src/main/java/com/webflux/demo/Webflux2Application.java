package com.webflux.demo;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@SpringBootApplication
@RestController
public class Webflux2Application {

	
	@GetMapping("users/{id}")
	Mono<User> userById(@PathVariable long id)
	{
		
		return Mono.just(User.builder().when(new Date()).id(1).build());
	}
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/events")
	Flux<User> users() 
	{
		Flux<User> userFlux = Flux.fromStream( Stream.generate( () -> new User(System.currentTimeMillis(), new Date())));
		Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
		return Flux.zip(userFlux, durationFlux)
		.map(Tuple2::getT1);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Webflux2Application.class, args);
	}
}