package com.riemannroch.appointapi.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Appoint {
    private Integer id;
    private String user;
    private LocalDateTime timestamp;
    private AppointType type;
    private Integer taskId;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
}
