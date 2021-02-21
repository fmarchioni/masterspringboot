package com.sample;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CamelSpringBootTest
public class MySpringBootApplicationTest {

	@Autowired
	private CamelContext camelContext;

	@Autowired
	private ProducerTemplate producerTemplate;

	@Test
	public void test() throws Exception {
		MockEndpoint mock = camelContext.getEndpoint("mock:stream:out", MockEndpoint.class);

		AdviceWith.adviceWith(camelContext, "hello",
				// intercepting an exchange on route
				r -> {
					// replacing consumer with direct component
					r.replaceFromWith("direct:start");
					// mocking producer
					r.mockEndpoints("stream*");
				}
		);

		// setting expectations
		mock.expectedMessageCount(1);
		mock.expectedBodiesReceived("FRANK");

		// invoking consumer
		producerTemplate.sendBody("direct:start", "frank");

		// asserting mock is satisfied
		mock.assertIsSatisfied();
	}
}
