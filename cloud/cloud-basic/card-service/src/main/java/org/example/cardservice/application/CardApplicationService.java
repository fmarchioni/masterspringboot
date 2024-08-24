package org.example.cardservice.application;

import org.example.cardservice.user.User;
import org.example.cardservice.user.UserServiceClient;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
class CardApplicationService {

	private final UserServiceClient userServiceClient;


	public CardApplicationService(UserServiceClient userServiceClient
			 ) {
		this.userServiceClient = userServiceClient;
	}

	public CardApplication registerApplication(CardApplicationDto applicationDTO) {
		User user = userServiceClient.registerUser(applicationDTO.user).getBody();
		CardApplication application = new CardApplication(UUID.randomUUID(),
				user, applicationDTO.cardCapacity);

		if (user.getStatus().equals(User.Status.OK)) {
		   application.setApplicationResult(ApplicationResult.granted());
		}
		else {
			application.setApplicationResult(ApplicationResult.rejected());
		}
		return application;


	}
}
