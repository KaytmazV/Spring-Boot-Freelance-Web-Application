package com.volkankaytmaz.backendproject2.mapper;

import com.volkankaytmaz.backendproject2.dto.ServiceDTO;
import com.volkankaytmaz.backendproject2.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    ServiceDTO toDTO(Service service);
    Service toEntity(ServiceDTO serviceDTO);
}

