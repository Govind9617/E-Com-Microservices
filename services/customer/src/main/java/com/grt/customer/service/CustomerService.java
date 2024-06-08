package com.grt.customer.service;

import com.grt.customer.controller.CustomerRequest;
import com.grt.customer.controller.CustomerResponse;
import com.grt.customer.entity.Customer;
import com.grt.customer.exceptions.CustomerNotFoundException;
import com.grt.customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(toCustomer(customerRequest));
        return customer.getId();
    }

    private static Customer toCustomer(CustomerRequest customerRequest) {
        if (customerRequest == null) {
            return null;
        }
        ;
        return Customer.builder().
                id(customerRequest.id()).firstName(customerRequest.firstName()).lastName(customerRequest.lastName()).
                email(customerRequest.email()).address(customerRequest.address()).
                build();

    }

    public void updateCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id()).orElseThrow(() -> new CustomerNotFoundException(String.format("customer not found :%s", customerRequest.id())));
        mergerCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setLastName(customerRequest.lastName());
        }
        if (StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }
        if (customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomer() {
        return customerRepository.findAll().stream().map(customerMapper::fromCustomer).
                collect(Collectors.toList());
    }

    public boolean existsById(String id) {
        return customerRepository.findById(id).isPresent();

    }


    public CustomerResponse findByID(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer).orElseThrow(
                () -> new CustomerNotFoundException(String.format("Customer not found: %s", customerId)));

    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
