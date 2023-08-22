package com.riemannroch.appointapi.controller;

import com.riemannroch.appointapi.dto.TaskCreateDto;
import com.riemannroch.appointapi.dto.TaskEditDto;
import com.riemannroch.appointapi.model.Task;
import com.riemannroch.appointapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    TaskService service;

    @PostMapping
    public Task create(@RequestBody TaskCreateDto taskCreateDto) {
        return service.create(taskCreateDto);
    }

    @GetMapping
    public List<Task> list() {
        return service.list();
    }

    @PatchMapping("/{id}")
    public Task edit(@RequestBody TaskEditDto taskEditDto, @PathVariable int id) {
        return service.edit(taskEditDto, id);
    }
}
