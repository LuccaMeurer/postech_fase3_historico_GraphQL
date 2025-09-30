package com.br.fiap.postech_fase3_historico.controller;

import com.br.fiap.postech_fase3_historico.dto.AppointmentHistoryDTO;
import com.br.fiap.postech_fase3_historico.service.AppointmentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppointmentHistoryController {

    private final AppointmentHistoryService service;

    @QueryMapping
    public AppointmentHistoryDTO appointmentHistoryById(@Argument Long id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<AppointmentHistoryDTO> appointmentHistories(
            @Argument int page,
            @Argument int size
    ) {
        return service.listAll(page, size);
    }

    @QueryMapping
    public List<AppointmentHistoryDTO> appointmentHistoriesByAppointment(
            @Argument Long appointmentId,
            @Argument int page,
            @Argument int size
    ) {
        return service.findByAppointment(appointmentId, page, size);
    }

    @QueryMapping
    public List<AppointmentHistoryDTO> appointmentHistoriesByPatient(
            @Argument Long patientId,
            @Argument int page,
            @Argument int size
    ) {
        return service.findByPatient(patientId, page, size);
    }

    @QueryMapping
    public List<AppointmentHistoryDTO> appointmentHistoriesByDoctor(
            @Argument Long doctorId,
            @Argument int page,
            @Argument int size
    ) {
        return service.findByDoctor(doctorId, page, size);
    }

    @QueryMapping
    public List<AppointmentHistoryDTO> myAppointments(
            @Argument int page,
            @Argument int size
    ) {
        return service.findByCurrentPatient(page, size);
    }
}
