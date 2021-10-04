package com.sample.ehcache.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	@Cacheable("numbers")
	public Long getFactorial(long id) {

		return calculateFactorial(id);
	}

	// @CachePut(value = "numbers", condition="#id>5")
	@CachePut(value = "numbers")
	public Long update(Long id) {
		return calculateFactorial(id);
	}

	@CacheEvict(value = "numbers", allEntries = true)
	public void evictData() {
		System.out.println("Cache evicted");

	}
	
	public long calculateFactorial(long n) {
		System.out.println("calculating...");
		return LongStream.rangeClosed(1, n).reduce(1, (long x, long y) -> x * y);
	}
}