package st_restful;

import java.io.Closeable;
import java.io.IOException;

import com.levent.webservice.controller.ICustomerController;
import com.levent.webservice.model.Customer;
import com.levent.webservice.model.response.CustomerPayloadResponse;
import com.levent.webservice.model.response.CustomerResponse;

public class CustomerControllerWrapper implements ICustomerController, Closeable {
	private CustomerControllerFootprint footPrint;
	private ICustomerController controller;
	
	public CustomerControllerWrapper(ICustomerController controller) {
		this.controller = controller;
		this.footPrint = new CustomerControllerFootprint(controller);
	}
	
	public ICustomerController footPrint() {
		return footPrint;
	}

	@Override
	public String testAPI() {
		return controller.testAPI();
	}

	@Override
	public CustomerResponse getCustomerById(long id) {
		return controller.getCustomerById(id);
	}

	@Override
	public CustomerResponse getCustomers() {
		return controller.getCustomers();
	}

	@Override
	public CustomerPayloadResponse createCustomer(Customer customer) {
		return controller.createCustomer(customer);
	}

	@Override
	public CustomerPayloadResponse updateCustomer(long id, Customer customer) {
		return controller.updateCustomer(id, customer);
	}

	@Override
	public CustomerPayloadResponse deleteCustomer(long id) {
		return controller.deleteCustomer(id);
	}

	@Override
	public void close() throws IOException {
		footPrint.close();
	}

}
