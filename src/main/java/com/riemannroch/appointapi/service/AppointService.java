package com.riemannroch.appointapi.service;

import com.riemannroch.appointapi.dto.AppointCreateDto;
import com.riemannroch.appointapi.model.Appoint;
import com.riemannroch.appointapi.repository.AppointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointService {

    @Autowired
    AppointRepository repository;

    @Autowired
    TaskService taskService;

    public List<Appoint> list() {
        return repository.list();
    }

    public Appoint create(AppointCreateDto appointCreateDto) {
        Appoint appoint = Appoint.builder()
                .type(appointCreateDto.getType())
                .user(appointCreateDto.getUser())
                .taskId(appointCreateDto.getTaskId())
                .timestamp(appointCreateDto.getTimestamp())
                .build();

        Appoint savedAppoint = repository.save(appoint);

        taskService.updateLoggedTime(savedAppoint.getTaskId());

        return savedAppoint;
    }
}
