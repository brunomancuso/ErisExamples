package st_restful;

import com.levent.webservice.model.Customer;

public class CustomerBuilder {
	private String firstName;
	private String lastName;
	private int age;
	private boolean isRegular;

	public CustomerBuilder firstName(String name) {
		this.firstName = name;
		return this;
	}

	public CustomerBuilder lastName(String name) {
		this.lastName = name;
		return this;
	}

	public CustomerBuilder age(int age) {
		this.age = age;
		return this;
	}

	public CustomerBuilder regular(boolean regular) {
		this.isRegular = regular;
		return this;
	}

	public Customer build() {
		return new Customer(firstName, lastName, age, isRegular);
	}
}
