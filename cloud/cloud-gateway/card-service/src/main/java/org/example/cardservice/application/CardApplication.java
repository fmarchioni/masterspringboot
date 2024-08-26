package org.example.cardservice.application;

import java.math.BigDecimal;
import java.util.UUID;

import org.example.cardservice.user.User;


class CardApplication {

	private final UUID uuid;
	private final User user;
	private final BigDecimal cardCapacity;
	private ApplicationResult applicationResult;

	CardApplication(UUID uuid, User user, BigDecimal cardCapacity) {
		this.uuid = uuid;
		this.user = user;
		if (!User.Status.OK.equals(user.getStatus())) {
			applicationResult = ApplicationResult.rejected();
		}
		this.cardCapacity = cardCapacity;
	}

	public UUID getUuid() {
		return uuid;
	}

	public User getUser() {
		return user;
	}

	public BigDecimal getCardCapacity() {
		return cardCapacity;
	}

	public ApplicationResult getApplicationResult() {
		return applicationResult;
	}

	void setApplicationResult(ApplicationResult applicationResult) {
		this.applicationResult = applicationResult;
	}
}
