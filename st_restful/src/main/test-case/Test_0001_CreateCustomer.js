
var module_customer_service = [
	"createCustomer:",
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
	var service = b.createCustomerService();
	service.footPrint().createCustomer(b.withCustomer().firstName("John").lastName("Doe").age(33).regular(true).build());
}