package com.riemannroch.appointapi.dto;

import com.riemannroch.appointapi.model.AppointType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointCreateDto {
    private String user;
    private LocalDateTime timestamp;
    private AppointType type;
    private Integer taskId;
}
