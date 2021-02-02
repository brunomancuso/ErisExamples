
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.threewaves.eris.engine.test_case.JUnitRuntime;

import st_restful.CustomerControllerWrapper;
import st_restful.CustomerServiceBuilder;

public class Test_0003_TestCaseInJUnit4 {
	private CustomerServiceBuilder b = JUnitRuntime.get().getBuilder("b", CustomerServiceBuilder.class);

	public static void main(String[] args) throws IOException {
		JUnitRuntime.insertCode();
	}

	@BeforeClass
	public static void beforeClass() throws IOException {
		JUnitRuntime.get().beforeAll();
	}

	@AfterClass
	public static void afterClass() throws IOException {
		JUnitRuntime.get().afterAll((module) -> {
			assertTrue("Module " + module + " failed", false);
		});
	}

	public final String[] module_customer_service = new String[] { "createCustomer:", "{", "  meta: {",
			"    isSuccessful: true", "  },", "  data: {", "    customers: [", "      {", "        id: 1,",
			"        firstName: 'John',", "        lastName: 'Doe',", "        age: 33,", "        isRegular: true",
			"      }", "    ]", "  }", "}", };

	@Test
	public void test() {
		CustomerControllerWrapper service = b.createCustomerService();
		service.footPrint()
				.createCustomer(b.withCustomer().firstName("John").lastName("Doe").age(33).regular(true).build());
	}

}