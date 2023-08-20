package com.riemannroch.appointapi.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Task {
    private Integer id;
    private String name;
    private AmountOfTime plannedTime;
    private AmountOfTime loggedTime;
    private LocalDateTime lastModified;
}
