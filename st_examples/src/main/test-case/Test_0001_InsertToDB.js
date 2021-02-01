
var module_database = [
	"MY_TABLE:",
	"	ID=1",
	"	XML=test",
];

var module_my_service = [
];

var module_third_party_authorization = [

];

function test() {	
	var service = s.createDatabaseService();
	service.insert(1, "test");	
}