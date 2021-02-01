var Simulator = Java.type('third_party_authorization.Simulator');

var module_third_party_authorization = [
	"authorize:",
	"<root>",
	"<date>210127 213307</date>",
	"<amount>10000</amount>",
	"</root>",
];

var module_my_service = [
	"someBusinessRules{",
	"  approved: true",
	"}",
];


var module_database = [
];

function test() {
	Simulator.put("authorize", "MESSAGE_APPROVED");	
	var service = s.createService();
	service.someBusinessRules(10000);	
	
}