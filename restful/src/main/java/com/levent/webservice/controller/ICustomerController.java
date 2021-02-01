package com.levent.webservice.controller;

import com.levent.webservice.model.Customer;
import com.levent.webservice.model.response.CustomerPayloadResponse;
import com.levent.webservice.model.response.CustomerResponse;

public interface ICustomerController {

	String testAPI();

	CustomerResponse getCustomerById(long id);

	CustomerResponse getCustomers();

	CustomerPayloadResponse createCustomer(Customer customer);

	CustomerPayloadResponse updateCustomer(long id, Customer customer);

	CustomerPayloadResponse deleteCustomer(long id);

}