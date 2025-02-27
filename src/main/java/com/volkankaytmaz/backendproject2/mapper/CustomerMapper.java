package com.volkankaytmaz.backendproject2.mapper;

import com.volkankaytmaz.backendproject2.dto.CustomerDTO;
import com.volkankaytmaz.backendproject2.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
    List<CustomerDTO> toDTOList(List<Customer> customers);




}
