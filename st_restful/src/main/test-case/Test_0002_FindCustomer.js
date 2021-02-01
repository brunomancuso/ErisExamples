
var module_customer_service = [
	"getCustomerById:",
	"{",
	"  meta: {",
	"    isSuccessful: true",
	"  },",
	"  data: {",
	"    customers: [",
	"      {",
	"        id: 1,",
	"        firstName: 'John',",
	"        lastName: 'Doe',",
	"        age: 33,",
	"        isRegular: true",
	"      }",
	"    ]",
	"  }",
	"}",
];


function test() {	
	//adding logging of request and responses
	var service = b.log().createCustomerService();
	//no footPrint
	var customerPayloadResponse = service.createCustomer(b.withCustomer().firstName("John").lastName("Doe").age(33).regular(true).build());
	//with footPrint
	service.footPrint().getCustomerById(customerPayloadResponse.data.customers[0].id);
}      