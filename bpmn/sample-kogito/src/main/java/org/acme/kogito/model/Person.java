package org.acme.kogito.model;


public class Person {

    private int amount;
	private int credit;
	private boolean existingLoan;
    private boolean approved;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCredit() {
		return credit;
	}

	public boolean isExistingLoan() {
		return existingLoan;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setExistingLoan(boolean existingLoan) {
		this.existingLoan = existingLoan;
	}


	@Override
	public String toString() {
		return "Person{" +
				"amount=" + amount +
				", credit=" + credit +
				", existingLoan=" + existingLoan +
				", approved=" + approved +
				'}';
	}


}
