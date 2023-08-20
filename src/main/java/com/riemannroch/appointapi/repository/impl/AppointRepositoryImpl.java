package com.riemannroch.appointapi.repository.impl;

import com.riemannroch.appointapi.model.Appoint;
import com.riemannroch.appointapi.model.AppointType;
import com.riemannroch.appointapi.repository.AppointRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AppointRepositoryImpl implements AppointRepository {

    private static int sequence = 2;
    private final List<Appoint> appointList = new ArrayList<>(
            Arrays.asList(Appoint.builder()
                            .id(0)
                            .type(AppointType.START)
                            .timestamp(LocalDateTime.of(2023, 8, 20, 12, 0, 0))
                            .taskId(0)
                            .user("test")
                            .lastModified(LocalDateTime.now())
                            .createdAt(LocalDateTime.now())
                            .build(),
                    Appoint.builder()
                            .id(1)
                            .type(AppointType.END)
                            .timestamp(LocalDateTime.of(2023, 8, 20, 12, 15, 12))
                            .taskId(0)
                            .user("test")
                            .lastModified(LocalDateTime.now())
                            .createdAt(LocalDateTime.now())
                            .build()
            )
    );

    @Override
    public List<Appoint> list() {
        return this.appointList;
    }

    @Override
    public Appoint save(Appoint appoint) {
        if (appoint.getId() == null) {
            appoint.setId(sequence++);
            appoint.setCreatedAt(LocalDateTime.now());
        }

        appoint.setLastModified(LocalDateTime.now());

        Appoint savedAppoint = findById(appoint.getId());
        if (savedAppoint != null) {
            appointList.set(appointList.indexOf(savedAppoint), appoint);
        } else {
            appointList.add(appoint);
        }
        return appoint;
    }

    @Override
    public Appoint findById(int id) {
        return appointList.stream()
                .filter(appoint -> appoint.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Appoint> findByTaskId(int taskId) {
        return appointList.stream().filter(appoint -> appoint.getTaskId() == taskId).toList();
    }
}
