package model;

import java.io.Serializable;

public class Name implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7329135212312161857L;
	private String firstName;
	private String lastName;

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// public Name(Name name) {
	// this.firstName = firstName;
	// this.lastName = lastName;
	// }

	public Name() {
		firstName = utils.NameFactory.emitFirstName();
		lastName = utils.NameFactory.emitLastName();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	public String exportString() {
		return firstName + "|" + lastName;
	}

}
