package com.volkankaytmaz.backendproject2.mapper;

import com.volkankaytmaz.backendproject2.dto.AppointmentDTO;
import com.volkankaytmaz.backendproject2.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.name")
    AppointmentDTO toAppointmentDTO(Appointment appointment);

    @Mapping(target = "customer", ignore = true)
    Appointment toEntity(AppointmentDTO appointmentDTO);

    List<AppointmentDTO> toDTOList(List<Appointment> appointments);
}

