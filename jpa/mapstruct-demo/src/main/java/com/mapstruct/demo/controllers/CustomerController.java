package com.mapstruct.demo.controllers;

 
import com.mapstruct.demo.mapstruct.dtos.CustomerGetDto;
import com.mapstruct.demo.mapstruct.dtos.CustomerPostDto;
import com.mapstruct.demo.mapstruct.mappers.MapStructMapper;
import com.mapstruct.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private MapStructMapper mapstructMapper;

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(
            MapStructMapper mapstructMapper,
            CustomerRepository userRepository
    ) {
        this.mapstructMapper = mapstructMapper;
        this.customerRepository = userRepository;
    }

    @PostMapping()
    public ResponseEntity<Void> create(
            @Valid @RequestBody CustomerPostDto customerPostDto
    ) {
        customerRepository.save(
                mapstructMapper.customerPostDtoToCustomer(customerPostDto)
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerGetDto> getById(
            @PathVariable(value = "id") int id
    ) {

        return new ResponseEntity<>(
                mapstructMapper.customerToCustomerGetDto(
                        customerRepository.findById(id).get()
                ),
                HttpStatus.OK
        );
    }
}
