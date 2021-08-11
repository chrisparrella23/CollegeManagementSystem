package model;

import java.io.Serializable;
import java.util.Random;

import utils.NameFactory;

public abstract class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -148507784441664090L;
	private Name name;
	private String phone;
	private String id;
	private static int idCounter = 1;
	Random rand = new Random();
	private static String[] areaCodes = { "631", "516", "718", "202", "686", "203" };
	private String areaCode;
	private static int phoneLength = 7;

	public Person() {
		name.setFirstName(NameFactory.emitFirstName());
		name.setLastName(NameFactory.emitLastName());
		phone = makePhoneNumber();
		this.id = String.valueOf(idCounter++);
	}

	public Person(Name name) {
		super();
		this.name = name;
		phone = makePhoneNumber();
		this.id = String.valueOf(idCounter++);
	}

	// public Person(String firstName, String lastName, String phone) {
	// this.name.setFirstName(firstName);
	// this.name.setLastName(lastName);
	// try {
	// if (phone.length() < 10 || phone.length() > 10) {
	// throw new IllegalPhoneException("Phone number must be 10 digits.");
	// }
	// this.phone = phone;
	// } catch (IllegalPhoneException e) {
	// System.out.println(e.getMessage());
	// }
	// this.id = String.valueOf(idCounter++);
	// }

	public Person(Name name, String phone) {
		this.name = name;
		try {
			if (phone.length() < 10 || phone.length() > 10) {
				throw new IllegalPhoneException("Phone number must be 10 digits.");
			}
			this.phone = phone;
		} catch (IllegalPhoneException e) {
			System.out.println(e.getMessage());
		}
		this.id = String.valueOf(idCounter++);
	}

	public String makePhoneNumber() {
		String areaCode = pickAreaCode();
		String randomPhone = "";
		for (int i = 0; i < phoneLength; i++) {
			int randomNumber = 48 + (int) (Math.random() * 9);
			randomPhone = randomPhone + (char) randomNumber;
		}
		return areaCode + randomPhone;
	}

	public String pickAreaCode() {
		Random rand = new Random();
		areaCode = areaCodes[rand.nextInt(areaCodes.length)];
		return areaCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return name.getFirstName();
	}

	public void setFirstName(String firstName) {
		this.name.setFirstName(firstName);
	}

	public String getLastName() {
		return name.getLastName();
	}

	public void setLastName(String lastName) {
		this.name.setLastName(lastName);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		try {
			if (phone.length() < 10 || phone.length() > 10) {
				throw new IllegalPhoneException("Phone number must be 10 digits long.");
			}
		} catch (IllegalPhoneException e) {
			System.out.println(e.getMessage());
		}

		this.phone = phone;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Person.idCounter = idCounter;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + name.getFirstName() + ", lastName=" + name.getLastName() + ", phone=" + phone
				+ ", id=" + id + "]";
	}

}
