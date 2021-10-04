package com.sample.ehcache.rest;

 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.ehcache.service.CacheService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class CacheController {
	private final CacheService cacheService;


	@GetMapping("/get/{id}")
	public ResponseEntity<Long> get(@PathVariable long id) {

		Long fact = cacheService.getFactorial(id);

		return new ResponseEntity<>(
				fact,
				HttpStatus.OK
				);
	}

	@GetMapping("/put/{id}")
	public ResponseEntity<Long> add(@PathVariable long id) {

		Long fact = cacheService.update(id);

		return new ResponseEntity<>(
				fact,
				HttpStatus.OK
				);
	}

	@GetMapping("/evict")
	public ResponseEntity<String> evict() {

		cacheService.evictData();

		return new ResponseEntity<>(
				"Cache cleaned",
				HttpStatus.OK
				);
	}
}
