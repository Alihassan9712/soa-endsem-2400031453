package com.semend.soa;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderProxyController {

	private final RestTemplate restTemplate;

	public OrderProxyController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/display")
	public ResponseEntity<String> displayAllOrders() {
		return restTemplate.getForEntity("http://OrderService/display", String.class);
	}

	@GetMapping("/instance")
	public String orderServiceInstance() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://OrderService/display", String.class);
		String port = response.getHeaders().getFirst("X-Order-Service-Port");
		if (port == null) {
			throw new IllegalStateException("OrderService response did not include its instance port");
		}
		return "OrderService instance on port " + port;
	}

	@GetMapping("/display/{id}")
	public ResponseEntity<String> displayOrderById(@PathVariable int id) {
		return restTemplate.getForEntity("http://OrderService/display/{id}", String.class, id);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addOrder(@RequestBody String requestBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return restTemplate.exchange("http://OrderService/add", HttpMethod.POST,
				new HttpEntity<>(requestBody, headers), String.class);
	}
}
