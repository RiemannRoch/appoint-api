package com.riemannroch.appointapi.repository;

import com.riemannroch.appointapi.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TaskRepository {
    Task save(Task task);

    List<Task> list();

    Task findById(int id);
}
