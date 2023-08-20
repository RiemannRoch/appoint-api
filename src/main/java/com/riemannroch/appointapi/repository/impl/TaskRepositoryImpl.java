package com.riemannroch.appointapi.repository.impl;

import com.riemannroch.appointapi.exception.NotFoundException;
import com.riemannroch.appointapi.model.AmountOfTime;
import com.riemannroch.appointapi.model.Task;
import com.riemannroch.appointapi.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private static Integer sequence = 0;

    private static final List<Task> tasks = new ArrayList<>();

    @Override
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(sequence++);
        }

        task.setLastModified(LocalDateTime.now());

        Task savedTask = findById(task.getId());
        if (savedTask != null) {
            tasks.set(tasks.indexOf(savedTask), task);
        } else {
            tasks.add(task);
        }
        return task;
    }

    @Override
    public List<Task> list() {
        return tasks;
    }

    @Override
    public Task findById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
