package com.thomasvitale.bookservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange(exchange -> exchange.matchers(EndpointRequest.toAnyEndpoint()).permitAll()
						.anyExchange().authenticated())
				.oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)
				.build();
	}
}

@RestController
@RequestMapping("books")
@Slf4j
class BookController {

	@GetMapping
	public Flux<Book> getBooks(@AuthenticationPrincipal JwtAuthenticationToken jwtToken) {
		log.info("Books browsed by " + jwtToken.getTokenAttributes().get("given_name"));
		return Flux.just(
				new Book("Harry Potter"),
				new Book("His Dark Materials"),
				new Book("The Hobbit"),
				new Book("The Lord of the Rings")
		);
	}

	@PostMapping
	public Mono<Book> createBook() {
		// Not implemented
		return Mono.empty();
	}
}

@Data @AllArgsConstructor
class Book {
	private String name;
}
