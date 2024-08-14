package com.boot.rest.base.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.rest.base.model.Customer;
import com.boot.rest.base.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Customer insert(Customer customerVO) {
    return this.customerRepository.save(customerVO);
  }

  @Override
  public List<Customer> findAll() {

    return this.customerRepository.findAll();
  }

  @Override
  public void delete(int id) {
    this.customerRepository.deleteById(id);
  }

  @Override
  public Customer findById(int id) {
    return this.customerRepository.findById(id).get();
  }

  @Override
  public Customer updateCustomer(int id, Customer customerVO) {
    customerVO.setId(id);
    return this.customerRepository.save(customerVO);
  }

}
