package com.volkankaytmaz.backendproject2.mapper;


import com.volkankaytmaz.backendproject2.dto.AppointmentDTO;
import com.volkankaytmaz.backendproject2.entity.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentDTO toAppointmentDTO(Appointment appointment);
    Appointment toEntity(AppointmentDTO appointmentDTO);
    List<AppointmentDTO> ToDTOList(List<Appointment> appointments);
}
