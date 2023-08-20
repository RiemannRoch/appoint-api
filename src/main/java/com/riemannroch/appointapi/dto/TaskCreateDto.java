package com.riemannroch.appointapi.dto;

import com.riemannroch.appointapi.model.AmountOfTime;
import lombok.Data;

@Data
public class TaskCreateDto {
    private String name;
    private AmountOfTime plannedTime;
}
