package com.volkankaytmaz.backendproject2.mapper;

import com.volkankaytmaz.backendproject2.dto.CustomerDTO;
import com.volkankaytmaz.backendproject2.dto.CustomerWithAppointmentsDTO;
import com.volkankaytmaz.backendproject2.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "appointments", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

    List<CustomerDTO> toDTOList(List<Customer> customers);

    @Mapping(target = "appointments", source = "appointments")
    CustomerWithAppointmentsDTO toDTOWithAppointments(Customer customer);
}

