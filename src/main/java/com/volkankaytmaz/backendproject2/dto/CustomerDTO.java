package com.volkankaytmaz.backendproject2.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CustomerDTO {
    private int id;
    private String name;
    private String phone;
    private LocalTime lastVisit;
    private LocalDateTime nextAppointment;
    private List<String> treatments;
}
