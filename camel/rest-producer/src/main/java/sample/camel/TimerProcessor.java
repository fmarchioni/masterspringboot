package sample.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TimerProcessor implements Processor {
	  public void process(Exchange exchange) throws Exception {	  
	        String id = exchange.getIn().getBody(String.class);
	    	Timer user = new Timer(id);
			exchange.getIn().setBody(user);
	    }
}
