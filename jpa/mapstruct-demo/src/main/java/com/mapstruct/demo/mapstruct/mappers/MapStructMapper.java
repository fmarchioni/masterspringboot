package com.mapstruct.demo.mapstruct.mappers;

import com.mapstruct.demo.entities.Customer;
import com.mapstruct.demo.mapstruct.dtos.*;
import org.mapstruct.Mapper;


@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {

    CustomerGetDto customerToCustomerGetDto(
            Customer customer
    );

    Customer customerPostDtoToCustomer(
            CustomerPostDto customerPostDTO
    );
}
