package org.example.userservice;

import java.util.UUID;


public class VerificationResult {

	private UUID userId;
	private Status status;

	private VerificationResult(UUID userId, Status status) {
		this.userId = userId;
		this.status = status;
	}

	public VerificationResult() {
	}

	public static VerificationResult passed(UUID userId) {
		return new VerificationResult(userId, Status.VERIFICATION_PASSED);
	}

	public static VerificationResult failed(UUID userId) {
		return new VerificationResult(userId, Status.VERIFICATION_FAILED);
	}

	public Status getStatus() {
		return status;
	}

	public UUID getUserId() {
		return userId;
	}

	public enum Status {
		VERIFICATION_PASSED,
		VERIFICATION_FAILED
	}
}
