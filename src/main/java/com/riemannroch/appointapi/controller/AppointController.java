package com.riemannroch.appointapi.controller;

import com.riemannroch.appointapi.dto.AppointCreateDto;
import com.riemannroch.appointapi.model.Appoint;
import com.riemannroch.appointapi.service.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appoint")
public class AppointController {

    @Autowired
    AppointService appointService;

    @GetMapping
    List<Appoint> list() {
        return appointService.list();
    }

    @PostMapping
    Appoint create(@RequestBody AppointCreateDto appointCreateDto) {
        return appointService.create(appointCreateDto);
    }
}
