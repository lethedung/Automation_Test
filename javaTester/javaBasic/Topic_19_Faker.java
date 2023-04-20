package javaBasic;

import java.util.Locale;

import com.github.javafaker.Faker;

public class Topic_19_Faker {
	public static void main(String[] args) {
		Locale locale = new Locale("vi");
		Faker faker = new Faker(locale);
		for (int i = 0; i < 100; i++) {
			System.out.println(faker.address().firstName());
		}
	}
}
