package com.volkankaytmaz.backendproject2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerWithAppointmentsDTO extends CustomerDTO {
    private List<AppointmentDTO> appointments;
}

