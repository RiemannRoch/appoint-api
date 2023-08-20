package com.riemannroch.appointapi.repository;

import com.riemannroch.appointapi.model.Appoint;

import java.util.List;


public interface AppointRepository {
    List<Appoint> list();
    Appoint save(Appoint appoint);
    Appoint findById(int id);
    List<Appoint> findByTaskId(int taskId);

}
