package com.riemannroch.appointapi.service;

import com.riemannroch.appointapi.dto.TaskCreateDto;
import com.riemannroch.appointapi.dto.TaskEditDto;
import com.riemannroch.appointapi.exception.NotFoundException;
import com.riemannroch.appointapi.model.AmountOfTime;
import com.riemannroch.appointapi.model.Appoint;
import com.riemannroch.appointapi.model.AppointType;
import com.riemannroch.appointapi.model.Task;
import com.riemannroch.appointapi.repository.AppointRepository;
import com.riemannroch.appointapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    AppointRepository appointRepository;

    public Task create(TaskCreateDto taskCreateDto) {
        Task task = Task.builder()
                .name(taskCreateDto.getName())
                .plannedTime(taskCreateDto.getPlannedTime())
                .loggedTime(new AmountOfTime())
                .build();

        return taskRepository.save(task);
    }

    public List<Task> list() {
        return taskRepository.list();
    }

    public Task edit(TaskEditDto taskEditDto, int id) {
        Task savedTask = taskRepository.findById(id);
        if (savedTask == null) {
            throw new NotFoundException("Task not found for id " + id);
        }

        if (taskEditDto.getName() != null) {
            savedTask.setName(taskEditDto.getName());
        }

        if (taskEditDto.getPlannedTime() != null) {
            savedTask.setPlannedTime(taskEditDto.getPlannedTime());
        }

        return taskRepository.save(savedTask);
    }

    public void updateLoggedTime(int id) {
        List<Appoint> appointList = new ArrayList<>(appointRepository.findByTaskId(id));

        appointList.sort(Comparator.comparing(Appoint::getTimestamp));

        Iterator<Appoint> appointIterator = appointList.listIterator();

        boolean isTimeRunning = false;
        LocalDateTime lastStartTime = LocalDateTime.now();
        AmountOfTime totalTime = new AmountOfTime();
        while (appointIterator.hasNext()) {
            Appoint currentAppoint = appointIterator.next();
            if (AppointType.START.equals(currentAppoint.getType())) {
                if (!isTimeRunning) {
                    isTimeRunning = true;
                    lastStartTime = currentAppoint.getTimestamp();
                }
            } else {
                if (isTimeRunning) {
                    isTimeRunning = false;
                    totalTime = totalTime.plus(
                            AmountOfTime.fromDuration(Duration.between(lastStartTime, currentAppoint.getTimestamp())));
                }
            }
        }

        var task = taskRepository.findById(id);
        task.setLoggedTime(totalTime);
        taskRepository.save(task);

    }

}
