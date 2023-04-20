package utilities;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	private Locale local = new Locale("en");
	private Faker faker = new Faker(local);

	public static DataHelper getDataHelper() {
		return new DataHelper();
	}

	public String getFirstName() {
		return faker.address().firstName();
	}

	public String getLastName() {
		return faker.address().lastName();
	}

	public String getEmailAdress() {
		return faker.internet().emailAddress();
	}

	public String getCityName() {
		return faker.address().city();
	}

	public String getPhoneBumber() {
		return faker.phoneNumber().phoneNumber();
	}

	public String getAdress() {
		return faker.address().streetAddress();
	}

	public String getPassword() {
		return faker.internet().password(8, 12, true, true);
	}
}
