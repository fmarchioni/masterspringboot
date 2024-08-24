package org.example.excluded;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


public class CustomLoadBalancerConfiguration {

	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		return ServiceInstanceListSupplier.builder()
				.withBlockingDiscoveryClient()
				.withZonePreference()
				.build(context);
	}
}
