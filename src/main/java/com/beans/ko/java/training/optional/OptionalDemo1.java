package com.beans.ko.java.training.optional;

import java.util.Optional;

public class OptionalDemo1 {

	public static void main(String[] args) {
		Optional<Address> address1 = Optional.of(new Address(Optional.ofNullable(null),Optional.of("New York")));
		Optional<Office> office1 = Optional.of(new Office("OF1",address1));
		Optional<Company> company1 = Optional.of(new Company("Door Never Closed",office1));
		
		Optional<Office> myOffice = company1.flatMap(Company::getOffice);
		Optional<Address> myAddress = office1.flatMap(Office::getAddress);
		Optional<String> myStreet = address1.flatMap(Address::getStreet);
		
		myStreet.ifPresent(System.out::println);
		if(myStreet.isPresent()){
			System.out.println(myStreet.get());
		} else {
			System.out.println("Street not found.");
		}
		
		//短写
		String city = company1.flatMap(Company::getOffice)
				.flatMap(Office::getAddress)
				.flatMap(Address::getStreet).orElse("city is not found.");
		System.out.println("City:"+city);
		
		company1.flatMap(Company::getOffice)
				.flatMap(Office::getAddress)
				.flatMap(Address::getCity)
				.ifPresent(System.out::println);
	}

}
