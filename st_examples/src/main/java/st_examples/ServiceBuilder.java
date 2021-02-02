package st_examples;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.threewaves.eris.engine.IBuilder;

import database.MyDatabaseService;
import third_party_authorization.MyService;

public class ServiceBuilder implements IBuilder {
	private List<Closeable> closeables = new ArrayList<>();

	public ServiceBuilder() {
	}

	@Override
	public Scope scope() {
		return Scope.TEST_CASE;
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

	public MyDatabaseService createDatabaseService() {
		MyDatabaseService service = closeable(new MyDatabaseService());
		return service;
	}

	public MyService createService() {
		MyService service = closeable(new MyService());
		return service;
	}

	private <T extends Closeable> T closeable(T c) {
		closeables.add(c);
		return c;
	}

	@Override
	public String name() {
		return "s";
	}

}
