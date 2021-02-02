package st_restful;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.threewaves.eris.engine.IBuilder;

import com.levent.webservice.controller.CustomerController;
import com.levent.webservice.controller.ICustomerController;
import com.levent.webservice.repository.impl.CustomerRepositoryCacheImpl;

public class CustomerServiceBuilder implements IBuilder {
	private List<Closeable> closeables = new ArrayList<>();
	private boolean log;

	// Gets created one per test case
	public CustomerServiceBuilder() {
		CustomerRepositoryCacheImpl.resetCache();
	}

	@Override
	public Scope scope() {
		return Scope.TEST_CASE;
	}

	@Override
	public String name() {
		return "b";
	}

	@Override
	public void destroy() {
		closeables.forEach(c -> {
			try {
				c.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public CustomerControllerWrapper createCustomerService() {
		ICustomerController main = new CustomerController();
		main = log ? new CustomerControllerLogger(main) : main;
		CustomerControllerWrapper service = closeable(new CustomerControllerWrapper(main));
		return service;
	}

	private <T extends Closeable> T closeable(T c) {
		closeables.add(c);
		return c;
	}

	public CustomerBuilder withCustomer() {
		return new CustomerBuilder();
	}

	public CustomerServiceBuilder log() {
		log = true;
		return this;
	}

}
