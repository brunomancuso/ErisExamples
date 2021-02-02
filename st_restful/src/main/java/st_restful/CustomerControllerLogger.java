package st_restful;

import java.util.function.Supplier;

import org.apache.commons.lang3.ClassUtils;

import com.google.gson.GsonBuilder;
import com.levent.webservice.controller.ICustomerController;
import com.levent.webservice.model.Customer;
import com.levent.webservice.model.response.CustomerPayloadResponse;
import com.levent.webservice.model.response.CustomerResponse;

public class CustomerControllerLogger implements ICustomerController {
	private final ICustomerController controller;

	public CustomerControllerLogger(ICustomerController controller) {
		this.controller = controller;
	}

	private <T> T log(Supplier<T> service, Object... request) {
		System.out.print("Request: ");
		String sep = "";

		for (Object r : request) {
			if (r == null) {
				continue;
			}
			if (String.class.isInstance(r) || ClassUtils.isPrimitiveOrWrapper(r.getClass())) {
				System.out.print(sep + r.toString());
				sep = ", ";
			} else {
				String json = toJson(r);
				sep = "\r\n";
				System.out.print(sep + json);
			}
		}
		System.out.println();

		T response = service.get();
		System.out.print("Response: ");
		if (response == null) {
			System.out.println("is null");
		} else if (response.getClass().isPrimitive()) {
			System.out.println(response.toString());
		} else {
			System.out.println(toJson(response));
		}
		return response;
	}

	private <T> String toJson(T r) {
		GsonBuilder b = new GsonBuilder();
		b.setPrettyPrinting();
		return b.create().toJson(r);
	}

	@Override
	public String testAPI() {
		return log(() -> controller.testAPI());
	}

	@Override
	public CustomerResponse getCustomerById(long id) {
		return log(() -> controller.getCustomerById(id), id);
	}

	@Override
	public CustomerResponse getCustomers() {
		return log(() -> controller.getCustomers());
	}

	@Override
	public CustomerPayloadResponse createCustomer(Customer customer) {
		return log(() -> controller.createCustomer(customer), customer);
	}

	@Override
	public CustomerPayloadResponse updateCustomer(long id, Customer customer) {
		return log(() -> controller.updateCustomer(id, customer), id, customer);
	}

	@Override
	public CustomerPayloadResponse deleteCustomer(long id) {
		return log(() -> controller.deleteCustomer(id), id);
	}
}
