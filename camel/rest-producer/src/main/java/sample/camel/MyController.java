
package sample.camel;

import java.security.SecureRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping(value = "/random/{id}")
	public String getRandomString(@PathVariable("id") Integer id) {
		return "HTTP GET Got " + randomString(id);
	}


	   
	@PostMapping("/time")
	public String getTime(@RequestBody Timer data) {
		return "HTTP POST Got " + data.getData();
	}
	
	String randomString(int len) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		
		return sb.toString();
	}
}
