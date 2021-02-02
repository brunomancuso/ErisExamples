package st_restful;

import java.io.Closeable;
import java.io.IOException;

import org.threewaves.eris.engine.footprint.Footprint;

import com.levent.webservice.controller.ICustomerController;
import com.levent.webservice.model.Customer;
import com.levent.webservice.model.response.CustomerPayloadResponse;
import com.levent.webservice.model.response.CustomerResponse;

public class CustomerControllerFootprint implements ICustomerController, Closeable {
	private final ICustomerController controller;
	private final Footprint log;

	public CustomerControllerFootprint(ICustomerController controller) {
		this.controller = controller;
		this.log = new Footprint("customer_service");
	}

	@Override
	public String testAPI() {
		return log.write("testAPI:\r\n", controller.testAPI());
	}

	@Override
	public CustomerResponse getCustomerById(long id) {
		return log.write("getCustomerById:\r\n", controller.getCustomerById(id));
	}

	@Override
	public CustomerResponse getCustomers() {
		return log.write("getCustomers:\r\n", controller.getCustomers());
	}

	@Override
	public CustomerPayloadResponse createCustomer(Customer customer) {
		return log.write("createCustomer:\r\n", controller.createCustomer(customer));
	}

	@Override
	public CustomerPayloadResponse updateCustomer(long id, Customer customer) {
		return log.write("updateCustomer:\r\n", controller.updateCustomer(id, customer));
	}

	@Override
	public CustomerPayloadResponse deleteCustomer(long id) {
		return log.write("deleteCustomer:\r\n", controller.deleteCustomer(id));
	}

	@Override
	public void close() throws IOException {
	}

}
